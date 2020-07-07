package cn.lixingyu.Apache.service;

import cn.lixingyu.Apache.entity.Address;
import cn.lixingyu.Apache.exception.AddressException;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/02/02 16:30
 */
public interface AddressService {

    void addAddress(Address address) throws AddressException;

    List<Address> queryAddress(String userUUID) throws AddressException;

    Address queryAddressAddressId(String addressId) throws AddressException;

}
