package cn.lixingyu.Apache.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Lxxxxxxy
 * @time 2020/01/09 16:47
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/item/user/head/**").addResourceLocations("file:D:/Images/Apache/upload/item/user/head/");
//        registry.addResourceHandler("/upload/item/user/shop/**").addResourceLocations("file:D:/Images/Apache/upload/item/user/shop/");
//        registry.addResourceHandler("/upload/item/user/product/**").addResourceLocations("file:D:/Images/Apache/upload/item/user/product/");
        registry.addResourceHandler("/upload/item/user/head/**").addResourceLocations("file:/home/Images/Apache/upload/item/user/head/");
        registry.addResourceHandler("/upload/item/user/shop/**").addResourceLocations("file:/home/Images/Apache/upload/item/user/shop/");
        registry.addResourceHandler("/upload/item/user/product/**").addResourceLocations("file:/home/Images/Apache/upload/item/user/product/");
    }
}