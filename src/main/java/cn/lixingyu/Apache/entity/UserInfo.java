package cn.lixingyu.Apache.entity;

import java.io.Serializable;

/**
 * @author Rlxy93
 * @time 2020/01/05 17:06
 */
public class UserInfo implements Serializable {
    //用户的唯一id，并作为用户头像的名称
    private String user_uuid;
    //登录账号
    private String user_account;
    private String user_password;
    //昵称
    private String user_username;
    //激活状态，0：未激活，1：已激活
    private Integer user_status;
    private String user_email_address;

    public UserInfo(String user_uuid, String user_account, String user_email_address) {
        this.user_uuid = user_uuid;
        this.user_account = user_account;
        this.user_email_address = user_email_address;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_uuid='" + user_uuid + '\'' +
                ", user_account='" + user_account + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_username='" + user_username + '\'' +
                ", user_status=" + user_status +
                ", user_email_address='" + user_email_address + '\'' +
                '}';
    }

    public String getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(String user_uuid) {
        this.user_uuid = user_uuid;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public Integer getUser_status() {
        return user_status;
    }

    public void setUser_status(Integer user_status) {
        this.user_status = user_status;
    }

    public String getUser_email_address() {
        return user_email_address;
    }

    public void setUser_email_address(String user_email_address) {
        this.user_email_address = user_email_address;
    }

    public UserInfo(String user_uuid, String user_account, String user_password, String user_username, Integer user_status, String user_email_address) {
        this.user_uuid = user_uuid;
        this.user_account = user_account;
        this.user_password = user_password;
        this.user_username = user_username;
        this.user_status = user_status;
        this.user_email_address = user_email_address;
    }
}
