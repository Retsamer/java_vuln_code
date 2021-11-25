package com.cinderella.demo.controller;

import com.cinderella.demo.dao.User;
import com.cinderella.demo.mapper.UserMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * @author Cinderella
 * @time 2021/8/24 14:16
 * @Description
 **/
@RestController
@RequestMapping("/xss")
public class XSS {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    /**
     * payload:http://127.0.0.1:8080/xss/reflect/vuln?str=<script>alert(1)</script>
     */
    @GetMapping("/reflect/vuln")
    public String index(@RequestParam("str") String str){
        logger.info(String.format("[+] input str is %s",str));
        Map<String,String> map = new HashMap<>();
        map.put("result",str);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(map);
    }


    @GetMapping("/stored/vuln")
    @ResponseBody
    public String storedVuln(@RequestParam("str") String content){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();
        userMapper.updateContent(username,content);
        String str1 = "用户留言更新成功!  ";
        String result = userMapper.findContentByUsername(username);
        String str2 = "用户留言查询成功!  ";
        Map<String,String> map = new HashMap<>();
        map.put("result",str1+result+str2);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String res = gson.toJson(map);
        return res;
    }

    @GetMapping("/reflect/sec")
    public String reflectSec(@RequestParam("str") String str){
        logger.info(String.format("[+] input str is %s",str));
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        String result = encode(str);
        map.put("result",result);
        return gson.toJson(map);
    }

    @GetMapping("/stored/sec")
    @ResponseBody
    public String storedSec(@RequestParam("str") String content){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();
        userMapper.updateContent(username,content);
        String result = encode(userMapper.findContentByUsername(username));
        Map<String,String> map = new HashMap<>();
        map.put("result",result);
        Gson gson = new Gson();
        String res = gson.toJson(map);
        return res;
    }

    private static String encode(String str){
        str = StringUtils.replace(str, "&", "&amp;");
        str = StringUtils.replace(str, "<", "&lt;");
        str = StringUtils.replace(str, ">", "&gt;");
        str = StringUtils.replace(str, "\"", "&quot;");
        str = StringUtils.replace(str, "'", "&#x27;");
        str = StringUtils.replace(str, "/", "&#x2F;");
        return str;
    }

}
