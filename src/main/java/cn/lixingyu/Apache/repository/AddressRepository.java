package cn.lixingyu.Apache.repository;

import cn.lixingyu.Apache.entity.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/02/02 16:28
 */
@Repository
public interface AddressRepository {

    @Insert("INSERT INTO ADDRESS (USER_UUID,USER_NAME,USER_PHONE,USER_ADDRESS)" +
            " VALUES(#{userUUID},#{userName},#{userPhone},#{userAddress})")
    void addAddress(Address address);

    @Select("SELECT ADDRESS_ID,USER_NAME,USER_PHONE,USER_ADDRESS FROM ADDRESS WHERE USER_UUID = #{userUUID}")
    List<Address> queryAddress(String userUUID);

    @Select("SELECT USER_NAME,USER_PHONE,USER_ADDRESS FROM ADDRESS WHERE ADDRESS_ID = #{addressId}")
    Address queryAddressAddressId(String addressId);

}
