package cn.lixingyu.Apache.entity;

import java.io.Serializable;

/**
 * @author Lxxxxxxy
 * @time 2020/01/05 17:06
 */
public class UserInfo implements Serializable {
    //用户的唯一id，并作为用户头像的名称
    private String userUuid;
    //登录账号
    private String userAccount;
    private String userPassword;
    //昵称
    private String userUsername;
    //激活状态，0：未激活，1：已激活
    private Integer userStatus;
    private String userEmailAddress;
    private Integer shopStatus;

    public UserInfo(String userUuid, String userAccount, String userEmailAddress) {
        this.userUuid = userUuid;
        this.userAccount = userAccount;
        this.userEmailAddress = userEmailAddress;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userUuid='" + userUuid + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userUsername='" + userUsername + '\'' +
                ", userStatus=" + userStatus +
                ", userEmailAddress='" + userEmailAddress + '\'' +
                ", shopStatus=" + shopStatus +
                '}';
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public UserInfo(String userUuid, String userAccount, String userPassword, String userUsername, Integer userStatus, String userEmailAddress, Integer shopStatus) {

        this.userUuid = userUuid;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userUsername = userUsername;
        this.userStatus = userStatus;
        this.userEmailAddress = userEmailAddress;
        this.shopStatus = shopStatus;
    }

    public UserInfo() {
    }
}
