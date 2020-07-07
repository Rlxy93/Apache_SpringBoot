package cn.lixingyu.Apache.repository;

import cn.lixingyu.Apache.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/01/31 16:33
 */
@Repository
public interface ProductRepository {

    @Select("SELECT * FROM PRODUCT WHERE PRODUCT_SHOP_ID = #{shopId}")
    List<Product> queryAllProductShopId(String shopId);

    @Insert("INSERT INTO PRODUCT (PRODUCT_ID," +
            "PRODUCT_SHOP_ID," +
            "PRODUCT_CATEGORY," +
            "PRODUCT_PRICE," +
            "PRODUCT_REMAINING," +
            "PRODUCT_DESC," +
            "PRODUCT_NAME) VALUES(#{productId}," +
            "#{productShopId}," +
            "#{productCategory}," +
            "#{productPrice}," +
            "#{productRemaining}," +
            "#{productDesc}," +
            "#{productName})")
    void addProduct(Product product);

    @Delete("DELETE FROM PRODUCT WHERE PRODUCT_ID = #{productId}")
    void deleteProduct(String productId);

    @Select("SELECT PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_ID FROM PRODUCT WHERE PRODUCT_NAME LIKE \'%${searchContent}%\'")
    List<Product> searchProduct(String searchContent);

    @Select("SELECT PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_ID FROM PRODUCT")
    List<Product> queryAllProduct();

    @Select("SELECT * FROM PRODUCT WHERE PRODUCT_ID = #{productId}")
    Product queryProduct(String productId);

    @Select("SELECT PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_ID FROM PRODUCT WHERE PRODUCT_CATEGORY = #{smallCategoryId}")
    List<Product> queryProductSmallCategoryId(String smallCategoryId);

}
