package cn.lixingyu.Apache.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rlxy93
 * @time 2020/01/05 17:19
 */
@Controller
public class MappingController {

    @RequestMapping(value = "/{path}")
    public String path(@PathVariable String path){
        return path;
    }
}
