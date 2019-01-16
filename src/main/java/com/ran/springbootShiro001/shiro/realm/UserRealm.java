package com.ran.springbootShiro001.shiro.realm;

import com.ran.springbootShiro001.bean.User;
import com.ran.springbootShiro001.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权...");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证...");


        //编写shiro判断逻辑，判断用户名和密码

        //1. 判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user = userService.findByUsername(token.getUsername());
        if (null == user) {
            //用户名不存在
            return null;//shiro底层会抛出UnknownAccountException
        }
        //2. 判断密码
        //参数1：需要返回给login方法的数据；参数2：数据库密码，shiro会自动判断
        return new SimpleAuthenticationInfo("", user.getPassword(),"");

    }
}
