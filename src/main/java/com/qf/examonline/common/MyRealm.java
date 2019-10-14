package com.qf.examonline.common;

import com.qf.examonline.entity.User;
import com.qf.examonline.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    @Lazy //使用redis缓存shiro，需要使用该注解
    private LoginService loginService;

    //获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //获取合法登录的用户的用户名
//        String name = (String) principalCollection.getPrimaryPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        //查询拥有的权限
//        List<String> perms = userService.login(name);
//        //创建授权信息对象
//        info.setStringPermissions(new HashSet<>(perms));
        return null;
    }

    //获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = (String)authenticationToken.getPrincipal();
        User user = loginService.login(name);

        SimpleAuthenticationInfo info = null;
        if(user == null){
            info = new SimpleAuthenticationInfo("", "",this.getName());
        }
        //身份认证信息对象
        //第一个参数，用户的身份信息、用户名
        //第二个参数，用户的凭证、密码
        //第三个参数，realm的名称
        //info = new SimpleAuthenticationInfo(name,user.getPassword(),this.getName());
        //如果md5中使用盐值，需要在认证信息对象中设置使用的盐值
        info = new SimpleAuthenticationInfo(name,user.getPassword(), ByteSource.Util.bytes("haha"), this.getName());
        return info;
    }

    public static void main(String[] args) {
        String hex = new SimpleHash("md5", "123","haha", 1).toHex();
        System.out.println(hex);
    }
}
