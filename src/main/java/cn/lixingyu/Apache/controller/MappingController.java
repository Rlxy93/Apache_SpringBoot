package cn.lixingyu.Apache.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lxxxxxxy
 * @time 2020/01/05 17:19
 */
@Controller
public class MappingController {

    @RequestMapping(value = "/{path}")
    public String path(@PathVariable String path){
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        if(principal != null && principal.equals("AADMIN") && path.startsWith("user")){
            return "admin";
        }
        return path;
    }

    @RequestMapping(value = "/user/{userPath}")
    public String userPath(@PathVariable String userPath){
        return "user/"+userPath;
    }

}
