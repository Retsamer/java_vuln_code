package com.cinderella.demo.controller.web;

import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cinderella
 * @time 2021/9/3 10:48
 * @Description
 **/
@Controller
public class PathRedirect {

    @GetMapping("/login")
    public String login1() {
        Subject userSubject = SecurityUtils.getSubject();
        if(userSubject.isRemembered() || userSubject.isAuthenticated()){
            return "index";
        }else{
            return "login";
        }

    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/scenes")
    public String scenes(){return "scenes";}

    @GetMapping("/scene01")
    public String scene01(){return "scene/scene01";}

    @GetMapping("/scene02")
    public String scene02(){
        return "scene/scene02";
    }

    @GetMapping("/scene03")
    public String scene03(){
        return "scene/scene03";
    }

    @GetMapping("/scene04")
    public String scene04(){
        return "scene/scene04";
    }

    @GetMapping("/scene05")
    public String scene05(){
        return "scene/scene05";
    }

    @GetMapping("/scene06")
    public String scene06(){
        return "scene/scene06";
    }


    @GetMapping("/test")
    @ResponseBody
    public String scene08(){
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("result","跳转成功");
        return gson.toJson(map);
    }
}
