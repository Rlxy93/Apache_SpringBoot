package cn.lixingyu.Apache.service.impl;

import cn.lixingyu.Apache.entity.Address;
import cn.lixingyu.Apache.exception.AddressException;
import cn.lixingyu.Apache.repository.AddressRepository;
import cn.lixingyu.Apache.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/02/02 16:30
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void addAddress(Address address) throws AddressException {
        if(address == null){
            throw new AddressException("地址为空！");
        }
        addressRepository.addAddress(address);
    }

    @Override
    public List<Address> queryAddress(String userUUID) throws AddressException {
        if(userUUID == null){
            throw new AddressException("用户信息错误！");
        }
        return addressRepository.queryAddress(userUUID);
    }

    @Override
    public Address queryAddressAddressId(String addressId) throws AddressException {
        if(addressId == null){
            throw new AddressException("地址信息错误！");
        }
        return addressRepository.queryAddressAddressId(addressId);
    }
}
