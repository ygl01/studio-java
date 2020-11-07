package com.ygl.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ygl
 * @description
 * @date 2020/11/6 10:37
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean  过滤 （第三步：连接到前端）
    @Bean
        public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //配置shiro的内置过滤器
        /*
            anon：匿名拦截器，不需要登录即可访问的资源，匿名用户或游客，一般用于过滤静态资源。
            authc：需要认证登录才能访问
            user：用户拦截器，表示必须存在用户
            perms：权限授权拦截器，验证用户是否拥有权限
                参数可写多个，表示需要某些权限才能通过，多个参数时写 perms[“user, admin”]，当有多个参数时必须每个参数都通过才算可以
            roles：角色授权拦截器，验证用户是或否拥有角色。
                   参数可写多个，表示某些角色才能通过，多个参数时写 roles[“admin,user”]，当有多个参数时必须每个参数都通过才算通过

         */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap();
        //授权，正常情况下，没有授权的会跳到未授权页面  添加某个权限才可以
//        filterMap.put("/user/add","perms[user:add]");
//        filterMap.put("/user/update","perms[user:update]");

        //授权   添加某个角色才可以
        filterMap.put("/user/add","roles[admin]");

        //拦截
        filterMap.put("/toLogin","anon");
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
        filterMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);

        //被拦截时 设置登录的请求
        bean.setLoginUrl("/toLogin");
        //跳转到未授权页面
        bean.setUnauthorizedUrl("/unauth");

        return bean;
    }

    //DafaultWebSecurityManager 安全对象  （第二步：接管对象）
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);

        return securityManager;

    }


    //创建Realm对象  需要自定义 （第一步：创建对象）
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
