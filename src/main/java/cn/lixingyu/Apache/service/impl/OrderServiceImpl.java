package cn.lixingyu.Apache.service.impl;

import cn.lixingyu.Apache.entity.Order;
import cn.lixingyu.Apache.exception.OrderException;
import cn.lixingyu.Apache.repository.OrderRepository;
import cn.lixingyu.Apache.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/02/04 16:25
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) throws OrderException {
        if (order == null) {
            throw new OrderException("获取订单信息失败！");
        }
        orderRepository.addOrder(order);
    }

    @Override
    public List<Order> queryOrderUserUUID(String userUUID) throws OrderException {
        if (userUUID == null) {
            throw new OrderException("获取订单信息失败！");
        }
        return orderRepository.queryOrderUserUUID(userUUID);
    }

    @Override
    public List<Order> queryOrderShopId(String shopId) throws OrderException {
        if (shopId == null) {
            throw new OrderException("获取订单信息失败！");
        }
        return orderRepository.queryOrderShopId(shopId);
    }

    @Override
    public void addLogistics(String logistics, String orderId) throws OrderException {
        if (logistics == null || orderId == null) {
            throw new OrderException("添加物流信息失败！");
        }
        orderRepository.addLogistics(logistics,orderId);
    }

    @Override
    public void confirmOrder(String orderId) throws OrderException {
        if (orderId == null) {
            throw new OrderException("确认收货失败！");
        }
        orderRepository.confirmOrder(orderId);
    }

    @Override
    public void updateOrderStatus(Integer statusNo, String orderId,String tradeNo) {
        orderRepository.updateOrderStatus(statusNo,orderId,tradeNo);
    }

    @Override
    public void updateTimeOutOrder(String orderId) {
        orderRepository.updateTimeOutOrder(orderId);
    }

    @Override
    public String queryOrderTradeNo(String orderId) {
        return orderRepository.queryOrderTradeNo(orderId);
    }
}
