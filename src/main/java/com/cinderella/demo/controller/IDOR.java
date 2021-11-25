package com.cinderella.demo.controller;

import com.cinderella.demo.dao.User;
import com.cinderella.demo.dto.ResponseCode;
import com.cinderella.demo.dto.StatusEnums;
import com.cinderella.demo.mapper.UserMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.catalina.util.ServerInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;


/**
 * @author Cinderella
 * @time 2021/9/1 16:06
 * @Description
 **/
@Controller
@RequestMapping("/idor")
public class IDOR {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/vuln01")
    @ResponseBody
    public String idorVuln(@RequestParam("str") Integer id){
        Gson gson = new Gson();
        Map<String,String> m = new HashMap<>();
        User user = userMapper.findById(id);
        m.put("result",user.toString());
        return gson.toJson(m);
    }


    @GetMapping("/sec01")
    @ResponseBody
    public String idorSec(@RequestParam("str") Integer id){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Gson gson = new Gson();
        Map<String,String> m = new HashMap<>();
        int currentId = user.getId();
        logger.info("[+] id "+id+"[+] currentid "+currentId);
        if(!(currentId==id)){
            m.put("result","当前用户权限不符合!");
            return gson.toJson(m);
        }
        User user1 = userMapper.findById(id);
        m.put("result",user1.toString());
        return gson.toJson(m);
    }

    @GetMapping("/vuln02")
    @ResponseBody
    public String idorVuln01(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Gson gson = new Gson();
        Map<String,String> m = new HashMap<>();
        m.put("tomcat_version", ServerInfo.getServerInfo());
        m.put("username", user.getUsername());
        m.put("login", "success");
        m.put("java_version", System.getProperty("java.version"));
        m.put("fastjson_version",JSON.VERSION);
        String str = gson.toJson(m);
        m.clear();
        m.put("result",str);
        return gson.toJson(m);
    }

    @GetMapping("/sec02")
    @ResponseBody
    public String idorSec02(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Gson gson = new Gson();
        Map<String,String> m = new HashMap<>();
        if(!user.getRole().equals("admin")){
            m.put("result","当前用户权限不足!");
            return gson.toJson(m);
        }
        m.put("tomcat_version", ServerInfo.getServerInfo());
        m.put("username", user.getUsername());
        m.put("login", "success");
        m.put("java_version", System.getProperty("java.version"));
        m.put("fastjson_version",JSON.VERSION);
        String str = gson.toJson(m);
        m.clear();
        m.put("result",str);
        return gson.toJson(m);
    }



}
