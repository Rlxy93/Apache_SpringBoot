package cn.lixingyu.Apache.service;

import cn.lixingyu.Apache.entity.UserInfo;
import cn.lixingyu.Apache.exception.UserException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author Lxxxxxxy
 * @time 2020/01/07 13:36
 */
public interface UserService {

    void register(UserInfo userInfo, CommonsMultipartFile headImage) throws UserException;
    void checkAccount(String account) throws UserException;
    void activeUser(String uuid) throws UserException;
    UserInfo login(String userAccount);
    UserInfo getUserInfo(String userAccount);

}
