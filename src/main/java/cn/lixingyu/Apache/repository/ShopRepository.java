package cn.lixingyu.Apache.repository;

import cn.lixingyu.Apache.entity.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lxxxxxxy
 * @time 2020/01/25 15:45
 */
@Repository
public interface ShopRepository {

    @Insert("INSERT INTO SHOP (SHOP_ID," +
            "SHOP_USER_ID," +
            "SHOP_CATEGORY," +
            "SHOP_NAME," +
            "SHOP_DESC," +
            "SHOP_ADDRESS," +
            "SHOP_PHONE," +
            "SHOP_CREATETIME," +
            "SHOP_LAST_EDIT_TIME) VALUES (#{shopId}," +
            "#{shopUserId}," +
            "#{shopCategory}," +
            "#{shopName}," +
            "#{shopDesc}," +
            "#{shopAddress}," +
            "#{shopPhone}," +
            "#{shopCreatetime}," +
            "#{shopLastEditTime}" +
            ")")
    void applyShop(Shop shop);

    @Update("UPDATE USER_INFO SET SHOP_STATUS = 1 WHERE USER_UUID = #{userUUID}")
    void updateShopStatus(String userUUID);

    @Select("SELECT SHOP_ID,SHOP_STATUS,SHOP_CATEGORY,SHOP_NAME,SHOP_DESC,SHOP_ADDRESS,SHOP_PHONE,SHOP_CREATETIME,SHOP_LAST_EDIT_TIME FROM SHOP WHERE SHOP_USER_ID = #{shopUserId}")
    Shop getShopInfoShopUserId(String shopUserId);

    @Update("UPDATE SHOP SET SHOP_CATEGORY = #{shopCategory}," +
            "SHOP_NAME = #{shopName}," +
            "SHOP_DESC = #{shopDesc}," +
            "SHOP_ADDRESS = #{shopAddress}," +
            "SHOP_PHONE = #{shopPhone}," +
            "SHOP_LAST_EDIT_TIME = #{shopLastEditTime} WHERE SHOP_USER_ID = #{shopUserId}")
    void editShopInfo(Shop shop);

    @Update("UPDATE UR SET ROLE_ID = 2 WHERE USER_UUID = #{userUUID}")
    void changeRole(String userUUID);

    @Select("SELECT SHOP_ID,SHOP_NAME,SHOP_CATEGORY,SHOP_ADDRESS,SHOP_PHONE,SHOP_STATUS,SHOP_CREATETIME,SHOP_LAST_EDIT_TIME FROM SHOP")
    List<Shop> queryAllShop();

    @Select("SELECT SHOP_NAME FROM SHOP WHERE SHOP_ID = #{shopId}")
    Shop queryShopName(String shopId);

}
