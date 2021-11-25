package com.cinderella.demo.controller;

import com.cinderella.demo.util.SecurityUtil;
import com.cinderella.demo.util.ssrfHook.SSRFException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cinderella
 * @time 2021/8/30 11:32
 * @Description
 **/
@RestController
@RequestMapping("/ssrf")
public class SSRF {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/vuln01")
    public String ssrfvuln(@RequestParam("str") String str){
        logger.info("[+] url is "+str);
        Map<String,String> map = new HashMap<>();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        map.put("result",SecurityUtil.URLConnection(str));
        return gson.toJson(map);
    }


    @RequestMapping("/sec01")
    public String ssrfCheckByWhitelistSec01(@RequestParam("str") String str){
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Map<String,String> map = new HashMap<>();
        if(SecurityUtil.checkSSRFByWhiteList(str)){
            map.put("result",SecurityUtil.URLConnection(str));
            return gson.toJson(map);
        }else {
            map.put("result","Illegal url!");
            return gson.toJson(map);
        }
    }

    @RequestMapping("/sec02")
    public String ssrfCheckByHook(@RequestParam("str") String url) {
        Map<String,String> map = new HashMap<>();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        if(!SecurityUtil.isHttp(url)){
            map.put("result","SSRF check failed");
            return gson.toJson(map);
        }
        try{
            SecurityUtil.startSSRFHook();
            map.put("result",SecurityUtil.URLConnection(url));
            return gson.toJson(map);
        }catch (SSRFException | IOException e){
            map.put("result","Illegal url!");
            return gson.toJson(map);
        }finally {
            SecurityUtil.stopSSRFHook();
        }
    }
    
}
