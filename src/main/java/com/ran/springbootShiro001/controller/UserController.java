package com.ran.springbootShiro001.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, String password) {
        /**
         * 使用shiro编写认证操作
         */
        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录方法
        try {
            //只要执行login方法，就会去执行UserRealm中的认证逻辑
            subject.login(token);

            //如果没有异常，代表登录成功
            //跳转到textThymeleaf页面，代表主页
            return "登录成功，跳转到主页";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            //登录失败
            return "用户名不存在,跳转到登录页";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return "密码错误,跳转到登录页";
        }
    }
}
