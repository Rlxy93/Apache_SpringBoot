package cn.lixingyu.Apache.entity;

import java.util.Date;

/**
 * @author Lxxxxxxy
 * @time 2020/01/10 11:38
 */
public class Shop {
    private String shopId;
    private String shopUserId;
    private Integer shopCategory;
    private String shopName;
    private String shopDesc;
    private String shopAddress;
    private String shopPhone;
    private Integer shopStatus;
    private Date shopCreatetime;
    private Date shopLastEditTime;

    public String getShopId() {
        return shopId;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId='" + shopId + '\'' +
                ", shopUserId='" + shopUserId + '\'' +
                ", shopCategory=" + shopCategory +
                ", shopName='" + shopName + '\'' +
                ", shopDesc='" + shopDesc + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopPhone='" + shopPhone + '\'' +
                ", shopStatus=" + shopStatus +
                ", shopCreatetime=" + shopCreatetime +
                ", shopLastEditTime=" + shopLastEditTime +
                '}';
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopUserId() {
        return shopUserId;
    }

    public void setShopUserId(String shopUserId) {
        this.shopUserId = shopUserId;
    }

    public Integer getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(Integer shopCategory) {
        this.shopCategory = shopCategory;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Date getShopCreatetime() {
        return shopCreatetime;
    }

    public void setShopCreatetime(Date shopCreatetime) {
        this.shopCreatetime = shopCreatetime;
    }

    public Date getShopLastEditTime() {
        return shopLastEditTime;
    }

    public void setShopLastEditTime(Date shopLastEditTime) {
        this.shopLastEditTime = shopLastEditTime;
    }

    public Shop(String shopId, String shopUserId, Integer shopCategory, String shopName, String shopDesc, String shopAddress, String shopPhone, Integer shopStatus, Date shopCreatetime, Date shopLastEditTime) {
        this.shopId = shopId;
        this.shopUserId = shopUserId;
        this.shopCategory = shopCategory;
        this.shopName = shopName;
        this.shopDesc = shopDesc;
        this.shopAddress = shopAddress;
        this.shopPhone = shopPhone;
        this.shopStatus = shopStatus;
        this.shopCreatetime = shopCreatetime;
        this.shopLastEditTime = shopLastEditTime;
    }

    public Shop() {
    }
}
