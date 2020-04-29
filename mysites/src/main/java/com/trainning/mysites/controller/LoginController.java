package com.trainning.mysites.controller;


import com.trainning.mysites.domain.User;
import com.trainning.mysites.domain.UserLogin;
import com.trainning.mysites.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;


/*
*登录的实现：
*   1.用户输入账号密码，并提交
*   2.控制器获得账号密码，去数据库中比对查找相应的用户信息
*   3.如果用户正确转向主界面，否则回到登录界面并提示错误
*为了实现登录：
*   1.定义一个JavaBean：UserLogin用来存储登陆时的用户名和密码，分开实体User的验证和登录验证
*   2.在DAO访问中添加通过account查询用户信息的方法，service中也要条件
*操作权限的处理：
* 1.用户---》角色---》功能---》资源， 可以用spring security 或者apaoch shell来做
* 2.用户登录成功后，每次访问操作资源（功能），验证用户是否已经登录，如果登录了可访问否则不可访问，
* 可通过session中保存的user对象来判断
*
* */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    //get请求一般请求返回登录表单
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userLogin",new UserLogin());
        return "login";
    }
    //提交表单

    /**
     *  这里的@Valid没有实现应有的功能
     * @param user  核查的用户
     * @param result 核查返回的结果
     * @return
     */
    @PostMapping("/login")
    public String login(@Valid UserLogin user, BindingResult result,
                        HttpSession session, Model model){
        //用户登录的格式输入错误
        if (result.hasErrors()){
            return "redirect:/login";
        }
        // 检查用户身份的方法
        User u = userService.checkUser(user);
        if (u !=null){
            //如果返回为真，则将用户信息保存起来
            session.setAttribute("user",u);//去数据库中检索用户或者修改checkUser返回用户对象
            return "index"; //管理主界面
        }
        model.addAttribute("fail","账号或密码不正确");
        return "redirect:/login";   //出错返回登录界面
    }

}
