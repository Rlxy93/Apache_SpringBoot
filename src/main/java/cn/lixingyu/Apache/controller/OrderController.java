package cn.lixingyu.Apache.controller;

import cn.lixingyu.Apache.entity.*;
import cn.lixingyu.Apache.exception.AddressException;
import cn.lixingyu.Apache.exception.OrderException;
import cn.lixingyu.Apache.exception.ProductException;
import cn.lixingyu.Apache.exception.ShopException;
import cn.lixingyu.Apache.service.*;
import cn.lixingyu.Apache.service.impl.AlipayUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lxxxxxxy
 * @time 2020/02/04 16:28
 */
@Controller
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayUtil alipayUtil;

    private DefaultAlipayClient alipayClient;

    private AlipayTradePagePayRequest alipayRequest;

    @PostConstruct
    public void init(){
        alipayClient = alipayUtil.getDefaultAlipayClient();
        alipayRequest = alipayUtil.getAlipayTradePagePayRequest();
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequiresRoles(value = {"customer", "business", "admin"}, logical = Logical.OR)
    @PostMapping("/addOrder")
    @ResponseBody
    public String addOrder(@RequestParam String orderInfo) throws AlipayApiException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Order order = (Order) JSONObject.toBean(JSONObject.fromObject(orderInfo), Order.class);
        String userAccount = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        try {
            Product product = productService.queryProduct(order.getProductId());
            order.setPrice((float) SplitAndRound(product.getProductPrice() * order.getCount(), 2));
            order.setCommentStatus(0);
            order.setCreateTime(new Date());
            order.setOrderId(UUID.randomUUID().toString());
            order.setUserUUID(userInfo.getUserUuid());
            order.setStatus(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            order.setOrderTradeNo(sdf.format(new Date())+Math.round(Math.random()*1000));
            orderService.addOrder(order);
            modelMap.put("success", true);
        } catch (ProductException e) {
            modelMap.put("success", false);
            modelMap.put("message", e.getMessage());
        } catch (OrderException e) {
            modelMap.put("message", e.getMessage());
        }

        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setBody(order.getProductName());
        alipayBean.setOut_trade_no(order.getOrderId());
        alipayBean.setSubject(order.getProductName());
        alipayBean.setTotal_amount(String.valueOf(order.getPrice()) + "0");
//        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", charset, certpublickey, signType);
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                messageProperties.setContentEncoding("utf-8");
                messageProperties.setExpiration("60000");
                return message;
            }
        };
        rabbitTemplate.convertAndSend("DL_EXCHANGE","DL_KEY",alipayBean.getOut_trade_no(),messagePostProcessor);
        return result;
    }

    @RequiresRoles(value = {"customer", "business", "admin"}, logical = Logical.OR)
    @PostMapping("/user/queryOrder")
    @ResponseBody
    public Map<String, Object> queryOrder() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userAccount = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        try {
            List<Order> orders = orderService.queryOrderUserUUID(userInfo.getUserUuid());
            modelMap.put("success", true);
            modelMap.put("orders", orders);
        } catch (OrderException e) {
            modelMap.put("success", false);
            modelMap.put("message", e.getMessage());
        }
        return modelMap;
    }

    @RequiresRoles("business")
    @GetMapping("/user/managerOrder")
    public String managerOrder(Model model) {
        String userAccount = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        try {
            Shop shop = shopService.getShopInfoShopUserId(userInfo.getUserUuid());
            List<Order> orders = orderService.queryOrderShopId(shop.getShopId());
            model.addAttribute("orders", orders);
        } catch (ShopException e) {
            e.printStackTrace();
        } catch (OrderException e) {
            e.printStackTrace();
        }
        return "user/managerOrder";
    }

    @RequiresRoles("business")
    @PostMapping("/user/queryAddressAddressId")
    @ResponseBody
    public Map<String, Object> queryAddressAddressId(@RequestParam String addressId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            Address address = addressService.queryAddressAddressId(addressId);
            modelMap.put("success", true);
            modelMap.put("address", address);
        } catch (AddressException e) {
            modelMap.put("success", false);
            modelMap.put("message", e.getMessage());
        }
        return modelMap;
    }

    @RequiresRoles("business")
    @PostMapping("/user/addLogistics")
    @ResponseBody
    public Map<String, Object> addLogistics(@RequestParam String logistics, @RequestParam String orderId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            orderService.addLogistics(logistics, orderId);
            modelMap.put("success", true);
        } catch (OrderException e) {
            modelMap.put("success", false);
            modelMap.put("message", e.getMessage());
        }
        return modelMap;
    }

    @RequiresRoles(value = {"customer", "business", "admin"}, logical = Logical.OR)
    @PostMapping("/user/confirmOrder")
    @ResponseBody
    public Map<String, Object> confirmOrder(@RequestParam String orderId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            orderService.confirmOrder(orderId);
            modelMap.put("success", true);
        } catch (OrderException e) {
            modelMap.put("success", false);
            modelMap.put("message", e.getMessage());
        }
        return modelMap;
    }

    public double SplitAndRound(double a, int n) {
        a = a * Math.pow(10, n);
        return (Math.round(a)) / (Math.pow(10, n));
    }

    // 查询交易状态
    // WAIT_BUYER_PAY（交易创建，等待买家付款）
    // TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）
    // TRADE_SUCCESS（交易支付成功）
    // TRADE_FINISHED（交易结束，不可退款）
//    @GetMapping("/alipayNotify")
//    public void alipayNotify(HttpServletRequest request) throws AlipayApiException {
//        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
//        certAlipayRequest.setServerUrl(gatewayUrl);
//        certAlipayRequest.setAppId(appId);
//        certAlipayRequest.setPrivateKey(privateKey);
//        certAlipayRequest.setFormat("json");
//        certAlipayRequest.setCharset(charset);
//        certAlipayRequest.setSignType(signType);
//        certAlipayRequest.setCertPath(appcertpublickey);
//        certAlipayRequest.setAlipayPublicCertPath(certpublickey);
//        certAlipayRequest.setRootCertPath(rootcert);
//        DefaultAlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);
//        AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
//        alipayTradeQueryRequest.setBizContent("{\"out_trade_no\":\"cba995d1-b16c-4bee-9d79-17a556a687d7\",\"trade_no\":\"2020052122001424290500956215\"}");
//        AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.certificateExecute(alipayTradeQueryRequest);
//        System.out.println(request.getParameterMap());
//        System.out.println();
//    }

    //支付宝异步通知
    //0：未付款
    //1：已付款
    //2：订单已关闭
    //3：已发货
    //4：已收货
    @RequestMapping("/notifyUrl")
    public void notifyUrl(@RequestParam("out_trade_no") String outTradeNo,@RequestParam("trade_no") String tradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
        alipayTradeQueryRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\",\"trade_no\":\"" + tradeNo + "\"}");
        AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.certificateExecute(alipayTradeQueryRequest);
        String tradeStatus = alipayTradeQueryResponse.getTradeStatus();
        Integer statusNo = 0;
        switch (tradeStatus) {
            case "WAIT_BUYER_PAY":
                statusNo = 0;
                break;
            case "TRADE_SUCCESS":
                statusNo = 1;
                break;
            case "TRADE_CLOSED":
                statusNo = 2;
                break;
        }
        orderService.updateOrderStatus(statusNo,outTradeNo,tradeNo);
    }

    //支付
    @RequestMapping("/pay")
    @ResponseBody
    public String pay(AlipayBean alipayBean) throws AlipayApiException {
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }

    //支付失败
    @RabbitListener(queues = "REDIRECT_QUEUE")
    public void listenerQUEUE(String orderId) {
        orderService.updateTimeOutOrder(orderId);
    }

    //退款
    @RequestMapping("/refund")
    @ResponseBody
    public String refund(String orderTradeNo,String orderId) throws AlipayApiException {
        String price = orderService.queryOrderTradeNo(orderId)+'0';
        AlipayTradeRefundRequest alipayTradeRefundRequest = new AlipayTradeRefundRequest();
        alipayTradeRefundRequest.setBizContent("{\"out_trade_no\":\""+orderId+"\",\"refund_amount\":\""+price+"\"}");
        AlipayTradeRefundResponse alipayTradeQueryResponse = alipayClient.certificateExecute(alipayTradeRefundRequest);
        if(alipayTradeQueryResponse.getFundChange().equals("Y")){
            orderService.updateOrderStatus(2,orderId,orderTradeNo);
            return "<script>alert('退款成功！');window.location='./user';</script>";
        }
        return "<script>alert('退款失败！');window.location='./user';</script>";
    }

}
