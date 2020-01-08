package cn.lixingyu.Apache.controller;

import cn.lixingyu.Apache.entity.UserInfo;
import cn.lixingyu.Apache.exception.UserException;
import cn.lixingyu.Apache.service.UserService;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Rlxy93
 * @time 2020/01/05 17:37
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //注册用户
    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //图片获取
        CommonsMultipartFile userHeadImage = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            userHeadImage = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
            //把头像大小限制在1M以内
            if (userHeadImage.getSize() > 1000000) {
                modelMap.put("success", false);
                modelMap.put("message", "上传的图片文件大小超过了1M！");
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "上传的图片不能为空！");
            return modelMap;
        }
        String registerInfo = request.getParameter("registerInfo");
        UserInfo userInfo = (UserInfo) JSONObject.toBean(JSONObject.fromObject(registerInfo), UserInfo.class);
        //判断注册信息是否合法
        if (userInfo.getUserAccount().length() < 6 || userInfo.getUserAccount().length() > 10) {
            modelMap.put("success", false);
            modelMap.put("message", "登录账号长度不合法！");
            return modelMap;
        }
        if (userInfo.getUserUsername().length() < 4 || userInfo.getUserUsername().length() > 10) {
            modelMap.put("success", false);
            modelMap.put("message", "昵称长度不合法！");
            return modelMap;
        }
        if (userInfo.getUserPassword().length() < 8 || userInfo.getUserPassword().length() > 20) {
            modelMap.put("success", false);
            modelMap.put("message", "密码长度不合法！");
            return modelMap;
        }
        //默认未激活
        userInfo.setUserStatus(0);
        //设置uuid
        userInfo.setUserUuid(UUID.randomUUID().toString());
        userInfo.setUserAccount(userInfo.getUserAccount().toUpperCase());
        String md5 = new SimpleHash("md5", userInfo.getUserPassword(), userInfo.getUserAccount(), 5).toHex();
        userInfo.setUserPassword(md5);
        try {
            userService.register(userInfo, userHeadImage);
            modelMap.put("success", true);
            //注册成功，开始向RabbitMQ发送消息
            rabbitTemplate.convertAndSend("email.direct","sendEmail",
                    new UserInfo(userInfo.getUserUuid(),userInfo.getUserAccount(),userInfo.getUserEmailAddress()));
        } catch (UserException e) {
            modelMap.put("success", false);
            modelMap.put("message", e.getMessage());
        }
        return modelMap;
    }

    //检查账号是否存在
    @GetMapping("/checkAccount")
    @ResponseBody
    public Map<String,Object> checkAccount(@RequestParam("account") String account){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            userService.checkAccount(account);
            modelMap.put("success", true);
        } catch (UserException e) {
            modelMap.put("success", false);
            modelMap.put("message", e.getMessage());
        }
        return modelMap;
    }

    //激活新用户
    @GetMapping("/activeUser")
    @ResponseBody
    public String activeUser(@RequestParam String uuid){
        try {
            userService.activeUser(uuid);
            return "<script>alert('激活成功！');window.location='./login';</script>";
        } catch (UserException e) {
            return "<script>alert('激活失败！"+e.getMessage()+"');</script>";
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String loginInfo = request.getParameter("loginInfo");
        UserInfo userInfo = (UserInfo) JSONObject.toBean(JSONObject.fromObject(loginInfo), UserInfo.class);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserAccount(),userInfo.getUserPassword());
        try{
            token.setRememberMe(true);
            subject.login(token);
            modelMap.put("success",true);
        } catch (UserException e){
            modelMap.put("success",false);
            modelMap.put("message",e.getMessage());
        } catch (AuthenticationException e){
            modelMap.put("success",false);
            modelMap.put("message","用户名或密码错误！");
        }
        return modelMap;
    }

}
