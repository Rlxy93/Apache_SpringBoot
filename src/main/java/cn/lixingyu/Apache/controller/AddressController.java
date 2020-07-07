package cn.lixingyu.Apache.controller;

import cn.lixingyu.Apache.entity.Address;
import cn.lixingyu.Apache.entity.UserInfo;
import cn.lixingyu.Apache.exception.AddressException;
import cn.lixingyu.Apache.service.AddressService;
import cn.lixingyu.Apache.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lxxxxxxy
 * @time 2020/02/02 16:32
 */
@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @RequiresRoles(value = {"customer","business"},logical = Logical.OR)
    @PostMapping("/user/addAddress")
    @ResponseBody
    public String addAddress(Address address){
        String userAccount = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        address.setUserUUID(userInfo.getUserUuid());
        try {
            addressService.addAddress(address);
            return "<script>alert('添加成功！');window.location='../user';</script>";
        } catch (AddressException e) {
            return "<script>alert('添加失败！');window.location='../user';</script>";
        }
    }

    @RequiresRoles(value = {"customer","business","admin"},logical = Logical.OR)
    @PostMapping("/user/queryAddress")
    @ResponseBody
    public Map<String,Object> queryAddress(){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userAccount = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userService.getUserInfo(userAccount);
        try {
            List<Address> addresses = addressService.queryAddress(userInfo.getUserUuid());
            modelMap.put("addresses",addresses);
            modelMap.put("success",true);
        } catch (AddressException e) {
            modelMap.put("success",false);
            modelMap.put("message",e.getMessage());
        }
        return modelMap;
    }

}
