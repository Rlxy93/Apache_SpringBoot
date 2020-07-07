package cn.lixingyu.Apache.realm;

import cn.lixingyu.Apache.entity.RP;
import cn.lixingyu.Apache.entity.UserInfo;
import cn.lixingyu.Apache.exception.UserException;
import cn.lixingyu.Apache.repository.UserRepository;
import cn.lixingyu.Apache.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * @author Lxxxxxxy
 * @time 2020/01/08 17:26
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userAccount = (String) principals.getPrimaryPrincipal();
        Integer roleId = userRepository.queryRoles(userAccount);
        RP rp = new RP();
        switch(roleId){
            case 1:rp.init("customer","customer:*");break;
            case 2:rp.init("business","business:*");break;
            case 3:rp.init("admin","admin:*");break;
        }
        HashSet<String> permissionsSet = new HashSet<>();
        permissionsSet.add(rp.getPermissionsName());
        HashSet<String> rolesSet = new HashSet<>();
        rolesSet.add(rp.getRoleName());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionsSet);
        simpleAuthorizationInfo.setRoles(rolesSet);
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException, UserException {
        String userUUID = (String) token.getPrincipal();
        UserInfo userInfo = userService.login(userUUID);
        if(userInfo.getUserStatus() == 0){
            throw new UserException("用户未激活！");
        }
        SimpleAuthenticationInfo custom = null;
        //登录成功
        custom = new SimpleAuthenticationInfo(userInfo.getUserAccount(), userInfo.getUserPassword(), "custom");
        //设置用户加密的盐值，这里使用的是用户名
        custom.setCredentialsSalt(ByteSource.Util.bytes(userInfo.getUserAccount()));
        return custom;
    }
}
