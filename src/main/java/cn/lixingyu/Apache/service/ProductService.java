package cn.lixingyu.Apache.service;

import cn.lixingyu.Apache.entity.Product;
import cn.lixingyu.Apache.exception.ProductException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/01/31 16:37
 */
public interface ProductService {

    List<Product> queryAllProductShopId(String shopId);

    void addProduct(Product product, CommonsMultipartFile productImage) throws ProductException;

    void deleteProduct(String productId) throws ProductException;

    List<Product> searchProduct(String searchContent) throws ProductException;

    List<Product> queryAllProduct();

    Product queryProduct(String productId) throws ProductException;

    List<Product> queryProductSmallCategoryId(String smallCategoryId) throws ProductException;

}
