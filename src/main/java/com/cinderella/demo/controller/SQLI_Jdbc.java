package com.cinderella.demo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cinderella
 * @time 2021/8/24 14:20
 * @Description
 **/
@RestController
@RequestMapping("/sqli")
public class SQLI_Jdbc {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String driver = "com.mysql.cj.jdbc.Driver";

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     *payload:http://127.0.0.1:8080/sqli/jdbc/vuln?name=admin' or '1'='1
     */
    @GetMapping("/jdbc/vuln")
    public String jdbcSqliVul(@RequestParam("str") String str){
        StringBuilder result = new StringBuilder();
        Map<String,String> map = new HashMap<>();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            if(!con.isClosed()) logger.info("Connected to database!");
            Statement statement = con.createStatement();
            String sql = "select * from user where username = '"+str+"'";
            logger.info(String.format("[+]执行SQL语句为:%s",sql));
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String res_name = rs.getString("username");
                String res_pwd = rs.getString("password");
                String res_role = rs.getString("role");
                String res_phone = rs.getString("phone");
                String content = rs.getString("content");
                String info = String.format("用户名：%s 用户密码: %s 权限: %s 用户手机号码: %s 用户留言：%s\n",res_name,res_pwd,res_role,res_phone,content);
                result.append(info);
                logger.info(info);
            }
            rs.close();
            con.close();
        }catch (ClassNotFoundException e){
            logger.error("Sorry,can't find the Driver!");
            map.put("result","Sorry,can't find the Driver!");
            return gson.toJson(map);
        }catch (SQLException e){
            logger.error(e.toString());
            map.put("result",e.getMessage());
            return gson.toJson(map);
        }
        map.put("result",result.toString());
        return gson.toJson(map);
    }

    /**
     *sec url:http://127.0.0.1:8080/sqli/jdbc/sec?name=admin
     */
    @GetMapping("/jdbc/sec")
    public String jdbcSqliSec(@RequestParam("str") String str){
        StringBuilder result = new StringBuilder();
        Map<String,String> map = new HashMap<>();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            if(!con.isClosed()) logger.info("Connected to database!");
            String sql = "select * from user where username = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,str);
            logger.info(String.format("[+] 预编译执行的SQL语句为：%s",st));
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String res_name = rs.getString("username");
                String res_pwd = rs.getString("password");
                String res_role = rs.getString("role");
                String res_phone = rs.getString("phone");
                String content = rs.getString("content");
                String info = String.format("用户名：%s 用户密码: %s 权限: %s 用户手机号码: %s 用户留言：%s\n",res_name,res_pwd,res_role,res_phone,content);
                result.append(info);
                logger.info(info);
            }
            rs.close();
            con.close();
        }catch (ClassNotFoundException e){
            logger.error("Sorry,can't find the Driver!");
            map.put("result","Sorry,can't find the Driver!");
            return gson.toJson(map);
        }catch (SQLException e){
            logger.error(e.toString());
            map.put("result",e.getMessage());
            return gson.toJson(map);
        }
        if(result.toString().isEmpty()){
            map.put("result","预编译查询结果为空，无返回值");
        }else {
            map.put("result",result.toString());
        }
        return gson.toJson(map);
    }
}
