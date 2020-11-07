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
            anon：无需认证就可以访问
            authc：必须认证才可以访问
            user：必须拥有 记住我 功能才能访问
            perms：拥有对某个资源的权限才能访问
            role：拥有某个角色才能访问
         */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap();
        //授权，正常情况下，没有授权的会跳到未授权页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
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
