package cn.lixingyu.Apache.entity;

import java.util.Date;

/**
 * @author Lxxxxxxy
 * @time 2020/02/04 16:06
 */
public class Order {
    private String orderId;
    private String productId;
    private String shopId;
    private String userUUID;
    private Float price;
    private Integer count;
    private Date createTime;
    private Integer status;
    private String logistics;
    private Integer address;
    private Integer commentStatus;
    private String productName;
    private String orderTradeNo;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", userUUID='" + userUUID + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", createTime=" + createTime +
                ", status=" + status +
                ", logistics='" + logistics + '\'' +
                ", address=" + address +
                ", commentStatus=" + commentStatus +
                ", productName='" + productName + '\'' +
                ", orderTradeNo='" + orderTradeNo + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderTradeNo() {
        return orderTradeNo;
    }

    public void setOrderTradeNo(String orderTradeNo) {
        this.orderTradeNo = orderTradeNo;
    }

    public Order(String orderId, String productId, String shopId, String userUUID, Float price, Integer count, Date createTime, Integer status, String logistics, Integer address, Integer commentStatus, String productName, String orderTradeNo) {
        this.orderId = orderId;
        this.productId = productId;
        this.shopId = shopId;
        this.userUUID = userUUID;
        this.price = price;
        this.count = count;
        this.createTime = createTime;
        this.status = status;
        this.logistics = logistics;
        this.address = address;
        this.commentStatus = commentStatus;
        this.productName = productName;
        this.orderTradeNo = orderTradeNo;
    }

    public Order() {
    }
}
