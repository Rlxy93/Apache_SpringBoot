package cn.lixingyu.Apache.controller;

import cn.lixingyu.Apache.entity.Address;
import cn.lixingyu.Apache.entity.Product;
import cn.lixingyu.Apache.entity.Shop;
import cn.lixingyu.Apache.entity.UserInfo;
import cn.lixingyu.Apache.exception.AddressException;
import cn.lixingyu.Apache.exception.ProductException;
import cn.lixingyu.Apache.exception.ShopException;
import cn.lixingyu.Apache.service.AddressService;
import cn.lixingyu.Apache.service.ProductService;
import cn.lixingyu.Apache.service.ShopService;
import cn.lixingyu.Apache.service.UserService;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Lxxxxxxy
 * @time 2020/01/31 16:43
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private AddressService addressService;

    @RequiresRoles("business")
    @GetMapping("/user/managerProduct")
    public String queryAllProduct(Model model) {
        try {
            Subject subject = SecurityUtils.getSubject();
            String userAccount = (String) subject.getPrincipal();
            String userUuid = userService.getUserInfo(userAccount).getUserUuid();
            String shopId = shopService.getShopInfoShopUserId(userUuid).getShopId();
            List<Product> products = productService.queryAllProductShopId(shopId);
            model.addAttribute("products", products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/managerProduct";
    }

    @RequiresRoles("business")
    @PostMapping("/user/addProduct")
    @ResponseBody
    public Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String productInfo = request.getParameter("productInfo");
        Product product = (Product) JSONObject.toBean(JSONObject.fromObject(productInfo), Product.class);
        Subject subject = SecurityUtils.getSubject();
        String userAccount = (String) subject.getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        //获取商品图片
        CommonsMultipartFile productImage = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            productImage = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
            //把图片大小限制在1M以内
            if (productImage.getSize() > 1000000) {
                modelMap.put("success", false);
                modelMap.put("message", "上传的图片文件大小超过了1M！");
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "上传的图片不能为空！");
            return modelMap;
        }
        try {
            Shop shopInfo = shopService.getShopInfoShopUserId(userInfo.getUserUuid());
            product.setProductShopId(shopInfo.getShopId());
            product.setProductId(UUID.randomUUID().toString());
            productService.addProduct(product, productImage);
            modelMap.put("success", true);
        } catch (ProductException e) {
            modelMap.put("success", false);
            modelMap.put("message", e.getMessage());
        } catch (ShopException e) {
            modelMap.put("success", false);
            modelMap.put("message", e.getMessage());
        }
        return modelMap;
    }

    @RequiresRoles("business")
    @GetMapping("/user/deleteProduct")
    @ResponseBody
    public String deleteProduct(@RequestParam String productId) {
        try {
            productService.deleteProduct(productId);
            return "<script>alert('删除成功！');window.location='./managerProduct';</script>";
        } catch (ProductException e) {
            return "<script>alert('删除失败！');window.location='./managerProduct';</script>";
        }
    }

    @GetMapping("/searchProduct")
    public String searchProduct(@RequestParam String searchContent, Model model) {
        try {
            List<Product> products = productService.searchProduct(searchContent);
            model.addAttribute("products", products);
        } catch (ProductException e) {
            e.printStackTrace();
        }
        return "productList";
    }

    @PostMapping("/queryCarouselProduct")
    @ResponseBody
    public Map<String, Object> queryCarouselProduct() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Product> products = productService.queryAllProduct();
        LinkedList<Product> products1 = new LinkedList<>();
        for (Product p : products) {
            long round = Math.round(Math.random())*100;
            if (round == 0 && products1.size() < 4) {
                products1.add(p);
            }
        }
        modelMap.put("products", products1);
        modelMap.put("success", true);
        return modelMap;
    }

    @GetMapping("/productInfo")
    public String queryProduct(@RequestParam String productId,Model model){
        try {
            Product product = productService.queryProduct(productId);
            Shop shop = shopService.queryShopName(product.getProductShopId());
            model.addAttribute("shop",shop);
            model.addAttribute("product",product);
        } catch (ProductException e) {
            e.printStackTrace();
        } catch (ShopException e) {
            e.printStackTrace();
        }
        return "productInfo";
    }

    @RequiresRoles(value = {"customer","business"},logical = Logical.OR)
    @GetMapping("/buy")
    public String buy(@RequestParam String productId,@RequestParam String count,Model model){
        String userAccount = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        try {
            Product product = productService.queryProduct(productId);
            List<Address> addresses = addressService.queryAddress(userInfo.getUserUuid());
            model.addAttribute("product",product);
            model.addAttribute("count",count);
            model.addAttribute("addresses",addresses);
        } catch (ProductException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return "buy";
    }

    @GetMapping("/productList")
    public String productListSmallCategoryId(@RequestParam String productSmallCategoryId,Model model){
        try {
            List<Product> products = productService.queryProductSmallCategoryId(productSmallCategoryId);
            model.addAttribute("products",products);
        } catch (ProductException e) {
            e.printStackTrace();
        }
        return "productList";

    }


}
