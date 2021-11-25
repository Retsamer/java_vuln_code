package com.cinderella.demo.controller;

import com.cinderella.demo.dao.User;
import com.cinderella.demo.mapper.UserMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cinderella
 * @time 2021/8/24 14:45
 * @Description
 **/
@RestController
@RequestMapping("/sqli")
public class SQLI_Mybatis {
    @Autowired
    private UserMapper userMapper;

    /**
     * payload:http://127.0.0.1:8080/sqli/mybatis/vuln01?str=admin' or '1'='1
     */
    @GetMapping("/mybatis/vuln01")
    public String mybatisVuln01(@RequestParam("str") String name){
        StringBuilder sb = new StringBuilder();
        List<User> users = userMapper.findByNameVuln(name);
        for(User user : users ){
            sb.append(user);
            sb.append(System.getProperty("line.separator"));
        }
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("result",sb.toString());
        return gson.toJson(map);
    }

    /**
     *payload:http://127.0.0.1:8080/sqli/mybatis/vuln02?str=ad' or '1' ='1' %23
     */
    @GetMapping("/mybatis/vuln02")
    public String mybatisVuln02(@RequestParam("str") String name){
        StringBuilder sb = new StringBuilder();
        List<User> users = userMapper.likeFindByNameVuln(name);
        for(User user : users ){
            sb.append(user);
            sb.append(System.getProperty("line.separator"));
        }
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("result",sb.toString());
        return gson.toJson(map);
    }

    /**
     *payload:http://127.0.0.1:8080/sqli/mybatis/vuln03?str=1 desc%23
     */
    @GetMapping("/mybatis/vuln03")
    public String mybatisVuln03(@RequestParam("str") String str){
        StringBuilder sb = new StringBuilder();
        List<User> users = userMapper.orderByAgeVuln(str);
        for(User user : users ){
            sb.append(user);
            sb.append(System.getProperty("line.separator"));
        }
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("result",sb.toString());
        return gson.toJson(map);
    }

    /**
     *test url:http://127.0.0.1:8080/sqli/mybatis/sec01?str=admin
     */
    @GetMapping("/mybatis/sec01")
    public String mybatisSec01(@RequestParam("str") String name){
        StringBuilder sb = new StringBuilder();
        User users = userMapper.findByNameSec(name);
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        if(users.toString().isEmpty()){
            map.put("result","预编译查询结果为空，无返回值");
        }else{
            map.put("result",users.toString());
        }
        return gson.toJson(map);
    }

    /**
     *test url:http://127.0.0.1:8080/sqli/mybatis/sec02?name=ad
     */
    @GetMapping("/mybatis/sec02")
    public String mybatisSec02(@RequestParam("str") String name){
        StringBuilder sb = new StringBuilder();
        List<User> users = userMapper.likeFindByNameSec(name);
        for(User user : users ){
            sb.append(user);
            sb.append(System.getProperty("line.separator"));
        }
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        if(sb.toString().isEmpty()){
            map.put("result","预编译查询结果为空，无返回值");
        }else{
            map.put("result",sb.toString());
        }
        return gson.toJson(map);
    }

    /**
     *test url:http://127.0.0.1:8080/sqli/mybatis/sec03?name=ad
     */
    @GetMapping("/mybatis/sec03")
    public String mybatisSec03(@RequestParam("str") String str){
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        ArrayList<String> keys = new ArrayList<String>(){{
            add("id");
            add("age");
        }};
        boolean flag = false;
        for(String key : keys){
            if(key.equals(str)){
                flag = true;
                break;
            }else {
                flag = false;
            }
        }
        if(!flag) {
            map.put("result","input str is illegal");
            return gson.toJson(map);
        }
        StringBuilder sb = new StringBuilder();
        List<User> users = userMapper.orderByAgeVuln(str);
        for(User user : users ){
            sb.append(user);
            sb.append(System.getProperty("line.separator"));
        }
        map.put("result",sb.toString());
        return gson.toJson(map);
    }

}
