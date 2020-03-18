package cn.jief.community.modules.user.controller;

import cn.jief.community.modules.user.dto.UserReq;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description
 * @auther jfeng
 * @Date 2020/3/17 下午11:29
 */
@Controller
public class UserController {
    @PostMapping("/login.do")
    public String login(UserReq userReq, Model model){
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            String name = userReq.getUsername();
            String passwd = userReq.getPasswd();
            UsernamePasswordToken token = new UsernamePasswordToken(name, passwd);
            token.setRememberMe(true);
            try {
                currentUser.login( token );
                //if no exception, that's it, we're done!
            } catch ( UnknownAccountException uae ) {
                //username wasn't in the system, show them an error message?
                model.addAttribute("msg", "账号不存在");
                return "login";
            } catch ( IncorrectCredentialsException ice ) {
                //password didn't match, try again?
                model.addAttribute("msg", "密码错误");
                return "login";
            } catch ( LockedAccountException lae ) {
                //account for that username is locked - can't login.  Show them a message?
                model.addAttribute("msg", "账户已被冻结");
                return "login";
            } catch (AuthenticationException ae) {
                model.addAttribute("msg", "未知错误");
                return "login";
            }
        }
        return "redirect:/user/add.html";
    }

}

