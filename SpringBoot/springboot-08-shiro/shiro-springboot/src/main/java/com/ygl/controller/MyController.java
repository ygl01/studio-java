package com.ygl.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ygl
 * @description
 * @date 2020/11/5 20:45
 */
@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //获取当前用户请求
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);//执行登陆方法 ， 如果没有异常就说明ok‘
            return "index";
        }catch (UnknownAccountException e){//用户名不存在异常
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){//密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
    /**
     * @author ygl
     * @date 2020-11-07 10:29
     * 未授权，不能登录
    */
    @RequestMapping("/unauth")
    @ResponseBody
    public String unauthorized(){
        return "未授权，不能请求！";
    }
}
