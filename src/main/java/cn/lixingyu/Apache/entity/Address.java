package cn.lixingyu.Apache.entity;

/**
 * @author Lxxxxxxy
 * @time 2020/02/02 16:27
 */
public class Address {
    private Integer addressId;
    private String userUUID;
    private String userName;
    private String userPhone;
    private String userAddress;

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", userUUID='" + userUUID + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Address(Integer addressId, String userUUID, String userName, String userPhone, String userAddress) {
        this.addressId = addressId;
        this.userUUID = userUUID;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public Address() {
    }
}
