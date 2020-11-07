package com.ygl.config;

import com.ygl.pojo.User;
import com.ygl.service.UserSevice;
import com.ygl.utils.PasswordUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ygl
 * @description
 * @date 2020/11/6 10:41
 */
//自定义的 UserRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserSevice userSevice;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权功能=》doGetAuthorizationInfo！");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //进行授权
//        info.addStringPermission("user:add");
        //获取当前对象
        Subject subject = SecurityUtils.getSubject();
        //从认证中拿到当前登录的user对象
        User currentUser = (User) subject.getPrincipal();
        //设置当前用户的权限
//        info.addStringPermission(currentUser.getPerms());
        //设置角色
        info.addRole(currentUser.getRole());

        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证功能=》doGetAuthorizationInfo！");

        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //连接真实数据库
        User user = userSevice.queryByName(userToken.getUsername());
        //pwd代表是用户输入的密码数据
        String pwd ="";
        for (int i = 0; i < userToken.getPassword().length; i++) {
            pwd =pwd +userToken.getPassword()[i];
        }
        if (user == null){
            return null;
        }

        /*
        System.out.println("密码："+user.getPwd());
         */
        // 获取盐值
        String salt = user.getSalt();
        String password = PasswordUtils.getMd5(pwd, user.getName(), salt);
        char[] chars = password.toCharArray();
        userToken.setPassword(chars);
        System.out.println("password："+password);
        //密码认证   shiro来进行操作，防止获取到密码
        return new SimpleAuthenticationInfo(user,user.getPwd(),user.getName());

    }
}
