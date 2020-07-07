package cn.lixingyu.Apache.controller;

import cn.lixingyu.Apache.entity.Shop;
import cn.lixingyu.Apache.entity.UserInfo;
import cn.lixingyu.Apache.exception.ShopException;
import cn.lixingyu.Apache.service.ShopService;
import cn.lixingyu.Apache.service.UserService;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Lxxxxxxy
 * @time 2020/01/25 15:49
 */
@Controller
@RequestMapping("/user")
public class ShopController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    //申请店铺
    @PostMapping("/apply")
    @ResponseBody
    public Map<String,Object> applyShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String shopInfoJson = request.getParameter("applyInfo");
        Shop shopInfo = (Shop) JSONObject.toBean(JSONObject.fromObject(shopInfoJson), Shop.class);
        shopInfo.setShopId(UUID.randomUUID().toString());
        //构造Shop对象
        Subject subject = SecurityUtils.getSubject();
        String userAccount = (String) subject.getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        shopInfo.setShopUserId(userInfo.getUserUuid());
        shopInfo.setShopCreatetime(new Date());
        shopInfo.setShopLastEditTime(new Date());
        //获取店铺图片
        CommonsMultipartFile shopImage = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImage = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
            //把图片大小限制在1M以内
            if (shopImage.getSize() > 1000000) {
                modelMap.put("success", false);
                modelMap.put("message", "上传的图片文件大小超过了1M！");
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "上传的图片不能为空！");
            return modelMap;
        }
        //把构造好的shop对象存入数据库
        try{
            shopService.applyShop(shopInfo,shopImage,userInfo.getUserUuid());
            modelMap.put("success", true);
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("message", "申请店铺失败！");
        }
        return modelMap;
    }

    //加权限
    @RequiresRoles("business")
    @PostMapping("/getShopInfo")
    @ResponseBody
    public Map<String,Object> getShopInfo(){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        String userAccount = (String) subject.getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        try {
            Shop shopInfo = shopService.getShopInfoShopUserId(userInfo.getUserUuid());
            modelMap.put("shopInfo",shopInfo);
            modelMap.put("success",true);
        } catch (ShopException e) {
            modelMap.put("success",true);
            modelMap.put("message",e.getMessage());
        }
        return modelMap;
    }

    //加权限
    @RequiresRoles("business")
    @PostMapping("/editShopInfo")
    @ResponseBody
    public Map<String,Object> editShopInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //获取店铺图片
        CommonsMultipartFile shopImage = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImage = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
            //把图片大小限制在1M以内
            if (shopImage.getSize() > 1000000) {
                modelMap.put("success", false);
                modelMap.put("message", "上传的图片文件大小超过了1M！");
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "上传的图片不能为空！");
            return modelMap;
        }
        Subject subject = SecurityUtils.getSubject();
        String userAccount = (String) subject.getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        String shopInfo = request.getParameter("shopInfo");
        Shop shop = (Shop) JSONObject.toBean(JSONObject.fromObject(shopInfo), Shop.class);
        shop.setShopUserId(userInfo.getUserUuid());
        try {
            Shop shopInfo1 = shopService.getShopInfoShopUserId(userInfo.getUserUuid());
            shop.setShopId(shopInfo1.getShopId());
            shopService.editShopInfo(shop,shopImage);
            modelMap.put("shopInfo",shopInfo);
            modelMap.put("success",true);
        } catch (ShopException e) {
            modelMap.put("success",false);
            modelMap.put("message",e.getMessage());
        } catch (NullPointerException e){
            modelMap.put("success",false);
            modelMap.put("message","您还没有申请店铺，无法修改！");
        }
        return modelMap;
    }

    @RequiresRoles("admin")
    @GetMapping("/managerShopByAdmin")
    public String queryAllShop(Model model){
        try {
            List<Shop> shops = shopService.queryAllShop();
            model.addAttribute("shops",shops);
        } catch (Exception e) {
        }
        return "user/managerShopByAdmin";
    }


}
