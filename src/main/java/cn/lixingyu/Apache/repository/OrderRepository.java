package cn.lixingyu.Apache.repository;

import cn.lixingyu.Apache.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/02/04 16:22
 */
@Repository
public interface OrderRepository {
    @Insert("INSERT INTO USER_ORDER (ORDER_ID," +
            "PRODUCT_ID," +
            "SHOP_ID," +
            "USER_UUID," +
            "PRICE," +
            "COUNT," +
            "CREATE_TIME," +
            "STATUS," +
            "LOGISTICS," +
            "ADDRESS," +
            "COMMENT_STATUS," +
            "ORDER_TRADE_NO)" +
            "VALUES(#{orderId}," +
            "#{productId}," +
            "#{shopId}," +
            "#{userUUID}," +
            "#{price}," +
            "#{count}," +
            "#{createTime}," +
            "#{status}," +
            "#{logistics}," +
            "#{address}," +
            "#{commentStatus}," +
            "#{orderTradeNo})")
    void addOrder(Order order);

    @Select("SELECT USER_ORDER.ORDER_ID,USER_ORDER.PRODUCT_ID,PRODUCT.PRODUCT_NAME,USER_ORDER.PRICE,USER_ORDER.LOGISTICS,USER_ORDER.STATUS FROM USER_ORDER,PRODUCT WHERE USER_UUID = #{userUUID} AND USER_ORDER.PRODUCT_ID = PRODUCT.PRODUCT_ID")
    List<Order> queryOrderUserUUID(String userUUID);

    @Select("SELECT USER_ORDER.ADDRESS,USER_ORDER.ORDER_ID,USER_ORDER.PRODUCT_ID,PRODUCT.PRODUCT_NAME,USER_ORDER.PRICE,USER_ORDER.LOGISTICS,USER_ORDER.STATUS FROM USER_ORDER,PRODUCT WHERE SHOP_ID = #{shopId} AND USER_ORDER.PRODUCT_ID = PRODUCT.PRODUCT_ID")
    List<Order> queryOrderShopId(String shopId);

    @Update("UPDATE USER_ORDER SET LOGISTICS = #{logistics},STATUS = 3 WHERE ORDER_ID = #{orderId}")
    void addLogistics(String logistics,String orderId);

    @Update("UPDATE USER_ORDER SET STATUS = 4 WHERE ORDER_ID = #{orderId}")
    void confirmOrder(String orderId);

    @Update("UPDATE USER_ORDER SET STATUS = #{statusNo},ORDER_TRADE_NO = #{tradeNo} WHERE ORDER_ID = #{orderId}")
    void updateOrderStatus(Integer statusNo,String orderId,String tradeNo);

    @Update("UPDATE USER_ORDER SET STATUS = 2 WHERE STATUS = 0 AND ORDER_ID = #{orderId}")
    void updateTimeOutOrder(String orderId);

    @Select("SELECT DISTINCT(PRICE) FROM USER_ORDER WHERE ORDER_ID = #{orderId}")
    String queryOrderTradeNo(String orderId);

}
