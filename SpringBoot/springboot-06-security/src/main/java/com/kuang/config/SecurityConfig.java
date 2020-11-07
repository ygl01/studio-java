package com.kuang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author ygl
 * @description
 * @date 2020/11/5 11:40
 */

//AOP 横切
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //是链式编程
        //请求授权规则
        http.authorizeRequests()
                //首页所有人可以访问
                .antMatchers("/").permitAll()
                //访问level1下的所有页面只有拥有vip1的才可以访问
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //若没有权限则会到登录页面,需要开启登录的页面
        http.formLogin()
                //定制登录页
                .loginPage("/toLogin");
        //防止网站工具
        http.csrf().disable();//关闭csrf攻击功能   登出失败的原因

        //注销： 开启了注销功能
        http.logout()
         //跳转到首页
        .logoutSuccessUrl("/");

        //开启记住我功能
        http.rememberMe();

    }

    //认证
    //密码编码：There is no PasswordEncoder mapped for the id "null"
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //从数据库也可以，从内存也可以
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            //数据正常应该从数据库读取
                //用户名   密码   权限
            .withUser("ygl").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
            .and()
            .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
            .and()
            .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");//这是从内存认证
        // auth.jdbcAuthentication()  这是从jdbc认证

        /*
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(users.username("user").password("password").roles("vip1"))
                .withUser(users.username("admin").password("password").roles("vip1","vip2","vip3"));

         */

    }
}
