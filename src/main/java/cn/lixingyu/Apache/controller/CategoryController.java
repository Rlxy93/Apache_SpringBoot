package cn.lixingyu.Apache.controller;

import cn.lixingyu.Apache.entity.BigCategory;
import cn.lixingyu.Apache.entity.SmallCategory;
import cn.lixingyu.Apache.exception.CategoryException;
import cn.lixingyu.Apache.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lxxxxxxy
 * @time 2020/01/29 20:46
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequiresRoles("admin")
    @GetMapping("/user/managerBigCategory")
    public String managerBigCategory(Model model){
        try {
            List<BigCategory> bigCategorys = categoryService.queryAllBigCategory();
            model.addAttribute("bigCategorys",bigCategorys);
        } catch (Exception e) {
        }
        return "user/managerBigCategory";
    }

    @RequiresRoles("admin")
    @PostMapping("/user/addBigCategory")
    @ResponseBody
    public Map<String,Object> addBigCategory(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String bigCategoryName = request.getParameter("bigCategoryName");
        try {
            categoryService.addBigCategory(bigCategoryName);
            modelMap.put("success",true);
        } catch (CategoryException e) {
            modelMap.put("message",e.getMessage());
            modelMap.put("success",false);
        }
        return modelMap;
    }

    @PostMapping("/user/queryAllBigCategory")
    @ResponseBody
    public Map<String,Object> queryAllBigCategory(){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            List<BigCategory> bigCategories = categoryService.queryAllBigCategory();
            modelMap.put("bigCategories",bigCategories);
            modelMap.put("success",true);
        } catch (Exception e) {
            modelMap.put("message",e.getMessage());
            modelMap.put("success",false);
        }
        return modelMap;
    }

    @RequiresRoles("admin")
    @PostMapping("/user/addSmallCategory")
    @ResponseBody
    public Map<String,Object> addSmallCategoryUrl(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String bigCategoryId = request.getParameter("bigCategoryId");
        String smallCategoryName = request.getParameter("smallCategoryName");
        SmallCategory smallCategory = new SmallCategory();
        smallCategory.setBigCategoryId(Integer.valueOf(bigCategoryId));
        smallCategory.setSmallCategoryName(smallCategoryName);
        try {
            categoryService.addSmallCategory(smallCategory);
            modelMap.put("success",true);
        } catch (Exception e) {
            modelMap.put("message",e.getMessage());
            modelMap.put("success",false);
        }
        return modelMap;
    }

    @RequiresRoles("admin")
    @GetMapping("/user/managerSmallCategory")
    public String managerSmallCategory(Model model){
        try {
            List<SmallCategory> smallCategories = categoryService.queryAllSmallCategory();
            model.addAttribute("smallCategories",smallCategories);
        } catch (Exception e) {
            e.getMessage();
        }
        return "user/managerSmallCategory";
    }

    @RequiresRoles("admin")
    @GetMapping("/user/editSmallCategory")
    public String editSmallCategory(@RequestParam Integer id, Model model){
        try {
            SmallCategory smallCategory = categoryService.querySmallCategoryId(Integer.valueOf(id));
            model.addAttribute("smallCategory",smallCategory);
        } catch (CategoryException e) {
            e.getMessage();
        }
        return "user/editSmallCategory";
    }

    @RequiresRoles("admin")
    @GetMapping("/user/deleteSmallCategory")
    @ResponseBody
    public String deleteSmallCategory(@RequestParam Integer id){
        try {
            categoryService.deleteSmallCategory(id);
            return "<script>alert('删除成功！');window.location='./managerSmallCategory';</script>";
        } catch (CategoryException e) {
            return "<script>alert('删除失败！');window.location='./managerSmallCategory';</script>";
        }
    }

    @PostMapping("/user/queryAllSmallCategory")
    @ResponseBody
    public Map<String,Object> queryAllSmallCategory(){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            List<SmallCategory> smallCategories = categoryService.queryAllSmallCategory();
            modelMap.put("smallCategories",smallCategories);
            modelMap.put("success",true);
        } catch (Exception e) {
            modelMap.put("message",e.getMessage());
            modelMap.put("success",false);
        }
        return modelMap;
    }

}
