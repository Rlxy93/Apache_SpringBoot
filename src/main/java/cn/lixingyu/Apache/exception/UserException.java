package cn.lixingyu.Apache.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author Rlxy93
 * @time 2020/01/07 13:38
 */
public class UserException extends AuthenticationException {

    public UserException(String msg){
        super(msg);
    }

}
