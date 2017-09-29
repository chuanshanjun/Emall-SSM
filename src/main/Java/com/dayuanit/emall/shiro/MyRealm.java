package com.dayuanit.emall.shiro;

import com.dayuanit.emall.pojo.MallUser;
import com.dayuanit.emall.service.MallUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

public class MyRealm extends AuthenticatingRealm {

    private static final String REAL_NAME = "my_realm";

    /**
     *  这边不可以使用注入 因为我的MyRealm是在我的spring-config实例话的,所以要去spring配置文件中关联mallUserService
     *  myRealm在spring.xml中实例化然后给他注入mallUserService 且还需要set方法不然无法注入
     */

    private MallUserService mallUserService;

    public void setMallUserService(MallUserService mallUserService) {
        this.mallUserService = mallUserService;
    }

    @Override
    public String getName() {
        return REAL_NAME;
    }

    /**
     * doGet这个这个方法是获取认证，所以我们要拿到用户名和密码来认证 doGet这个方法有securityManager来调用他
     * 然后由subject来调用securityManager,在controller中获取subject
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        String username = upToken.getUsername();
        String password = String.valueOf(upToken.getPassword());

        mallUserService.login(username, password);
        //将这个对象返回出去shiro就知道你的用户名和密码以及realm的名字了 我可以在页面上通过shiro将用户名取出来
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
