package cn.jief.community.modules.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description
 * @auther jfeng
 * @Date 2020/3/17 下午11:29
 */
@Controller
public class UserController {
    @RequestMapping("/add")
    public String getAddHTML(){
        return "/user/add";
    }
}
