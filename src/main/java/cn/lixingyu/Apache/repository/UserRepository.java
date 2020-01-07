package cn.lixingyu.Apache.repository;

import cn.lixingyu.Apache.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Rlxy93
 * @time 2020/01/05 17:04
 */
@Mapper
public interface UserRepository {

    @Insert("INSERT INTO USER_INFO(USER_UUID,USER_ACCOUNT,USER_USERNAME,USER_PASSWORD,USER_STATUS,USER_EMAIL_ADDRESS) VALUES(#{user_uuid},#{user_account},#{user_username},#{user_password},#{user_status},#{user_email_address})")
    void register(UserInfo userInfo);

    @Select("SELECT USER_ACCOUNT FROM USER_INFO WHERE USER_ACCOUNT = #{account}")
    UserInfo checkAccount(String account);

    @Update("UPDATE USER_INFO SET USER_STATUS = 1 WHERE USER_UUID = #{uuid}")
    void activeUser(String uuid);

}
