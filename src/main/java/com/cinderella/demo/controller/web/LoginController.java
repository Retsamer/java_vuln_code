package com.cinderella.demo.controller.web;

import com.cinderella.demo.dao.User;
import com.cinderella.demo.dto.ResponseCode;
import com.cinderella.demo.dto.StatusEnums;
import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static org.apache.shiro.util.ThreadContext.getSubject;

/**
 * @author Cinderella
 * @time 2021/9/1 10:33
 * @Description
 **/
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录操作
     * @return
     */
    @PostMapping("/userLogin")
    @ResponseBody
    public String login(@RequestBody String body) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        String validateCode = jsonObject.get("validateCode").getAsString();
        Boolean rememberMe = jsonObject.get("rememberMe").getAsBoolean();

        logger.info("登录请求-start");


        if(validateCode == null || validateCode == ""){
            return gson.toJson(ResponseCode.error(StatusEnums.PARAM_NULL));
        }

        Session session = SecurityUtils.getSubject().getSession();
        validateCode = validateCode.toLowerCase();
        String v = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

        System.out.println("username "+username + " password "+password+" kaptcha " + validateCode + " rememberMe" + rememberMe + "  v" + v);
        session.removeAttribute("_come");

        if(!validateCode.equals(v)){
            return gson.toJson(ResponseCode.error(StatusEnums.VALIDATECODE_ERROR));
        }

        Subject userSubject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            // 登录验证
            userSubject.login(token);
            return gson.toJson(ResponseCode.success());
        } catch (UnknownAccountException e) {
            return gson.toJson(ResponseCode.error(StatusEnums.ACCOUNT_UNKNOWN));
        } catch (DisabledAccountException e) {
            return gson.toJson(ResponseCode.error(StatusEnums.ACCOUNT_IS_DISABLED));
        } catch (IncorrectCredentialsException e) {
            return gson.toJson(ResponseCode.error(StatusEnums.INCORRECT_CREDENTIALS));
        } catch (AuthenticationException e) {
            return gson.toJson(ResponseCode.error(StatusEnums.AUTH_ERROR));
        } catch (Throwable e) {
            e.printStackTrace();
            return gson.toJson(ResponseCode.error(StatusEnums.SYSTEM_ERROR));
        }
    }

    @RequestMapping("/index")
    public String index(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/auth")
    @ResponseBody
    public String auth() {
        return "已成功登录";
    }
    

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    public String logout() {
        getSubject().logout();
        return "login";
    }
}
