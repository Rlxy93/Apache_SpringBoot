package cn.lixingyu.Apache.repository;

import cn.lixingyu.Apache.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Lxxxxxxy
 * @time 2020/01/05 17:04
 */
@Mapper
@Repository
public interface UserRepository {

    @Insert("INSERT INTO USER_INFO(USER_UUID,USER_ACCOUNT,USER_USERNAME,USER_PASSWORD,USER_EMAIL_ADDRESS) VALUES(#{userUuid},#{userAccount},#{userUsername},#{userPassword},#{userEmailAddress})")
    void register(UserInfo userInfo);

    @Select("SELECT USER_ACCOUNT FROM USER_INFO WHERE USER_ACCOUNT = #{account}")
    UserInfo checkAccount(String account);

    @Update("UPDATE USER_INFO SET USER_STATUS = 1 WHERE USER_UUID = #{uuid}")
    void activeUser(String uuid);

    //1表示customer
    @Insert("INSERT INTO UR (USER_UUID,ROLE_ID) VALUES(#{uuid},1)")
    void setRole(String uuid);

    @Select("SELECT ROLE_ID FROM UR WHERE USER_UUID = (SELECT USER_UUID FROM USER_INFO WHERE USER_ACCOUNT = #{userAccount})")
    Integer queryRoles(String userAccount);

    @Select("SELECT * FROM USER_INFO WHERE USER_ACCOUNT = #{userAccount}")
    UserInfo login(String userAccount);

    @Select("SELECT USER_USERNAME,USER_UUID,USER_EMAIL_ADDRESS,SHOP_STATUS FROM USER_INFO WHERE USER_ACCOUNT = #{userAccount}")
    UserInfo getUserInfo(String userAccount);

}
