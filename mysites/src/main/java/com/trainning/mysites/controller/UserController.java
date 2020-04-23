package com.trainning.mysites.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trainning.mysites.domain.User;
import com.trainning.mysites.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/*
* 1.查询所有的符合条件的用户信息，并分页传送给界面
* 2.编辑方法从数据库中查询需要的数据
*
* 3.存储方法把新增的用户或编辑后用户细腻存储到数据库
* 4.删除用户
* 5.锁定用户，有效状态
* */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据条件查询用户信息
     * @param kw    查询关键字即条件
     * @param model 模型对象，也是视图(界面)的上下文环境对象
     * @param pageable  分页信息对象，包含了分页需要的基本信息，如当前页码，每页需要的条数
     * @return  字符串，代表了界面文件
     */
    @RequestMapping("/listusers")
    public String list(String kw, Model model, Pageable pageable){
        if (kw!=null) kw = "%"+kw+"%";
        if (kw == null) kw ="%%";
        Page<User> ppu = userService.findAll(kw, pageable);
        model.addAttribute("pages",ppu);
        return "listusers"; //返回界面
    }

    @GetMapping({"/edituser","/edituser/{uid}"})    //对单个用户进行操作
    public String edit(@PathVariable(name = "uid",required = false)Integer uid,Model model){
        User u = new User();
        if(uid!=null&&uid>0){
            u = userService.findById(uid);
        }
        model.addAttribute("user",u);
        return "edituser";
    }

    /**
     * 保存用户
     * @param user
     * @param result
     * @param attr  将存储信息提交到界面
     * @return
     */
    @PostMapping("/saveuser")
    public String save(@Valid User user, BindingResult result, RedirectAttributes attr){
        try {
            if (result.hasErrors()) {
                return "redirect:/edituser";
            }
            if(user.getUid()!=null&&user.getUid()>0){
                User u = userService.findById(user.getUid());
                user.setPassword(u.getPassword());
            }
            userService.save(user);
            attr.addFlashAttribute("ok","保存成功");
            return "redirect:/listusers";
            }catch (Exception ex){
            return "redirect:/edituser";
            }
    }

    @GetMapping("/deleteuser/{uid}")
    public String delete(@PathVariable(name = "uid")Integer uid){
        userService.deleteById(uid);
        return "redirect:/listusers";
    }

    @PostMapping("/deleteusers")
    public String deletes(String uids){
        System.out.println("============"+uids);
        List<User> users = new ArrayList<User>();
        JSONObject  json = JSONObject.parseObject(uids);
        JSONArray arr = json.getJSONArray("uids");//前端传递时使用uids作为json数据的键
        //{"uids":["4","4","5"]} 出现了这个问题
        int ilen = arr.size();
        for (int i = 0; i < ilen; i++) {
            users.add(userService.findById(arr.getInteger(i)));
        }
        userService.deletes(users);
        return "redirect:/listusers";
    }

    @GetMapping("/vailduser/{uid}")
    public String vailduser(@PathVariable(name = "uid")Integer uid){
        User user = userService.findById(uid);

        user.setvaildstate(user.getvaildstate());

        return "redirect:/listusers";
    }


}