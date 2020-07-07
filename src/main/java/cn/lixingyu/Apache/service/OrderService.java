package cn.lixingyu.Apache.service;

import cn.lixingyu.Apache.entity.Order;
import cn.lixingyu.Apache.exception.OrderException;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/02/04 16:25
 */
public interface OrderService {

    void addOrder(Order order) throws OrderException;

    List<Order> queryOrderUserUUID(String userUUID) throws OrderException;

    List<Order> queryOrderShopId(String shopId) throws OrderException;

    void addLogistics(String logistics,String orderId) throws OrderException;

    void confirmOrder(String orderId) throws OrderException;

    void updateOrderStatus(Integer statusNo,String orderId,String tradeNo);

    void updateTimeOutOrder(String orderId);

    String queryOrderTradeNo(String orderId);

}
