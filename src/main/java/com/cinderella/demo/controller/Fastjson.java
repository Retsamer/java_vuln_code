package com.cinderella.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Cinderella
 * @time 2021/9/2 16:43
 * @Description
 **/
@Controller
@RequestMapping("/fastjson")
public class Fastjson {

    @PostMapping("/vuln")
    public JSONObject fastjsonvuln(@RequestBody String str){
        return JSON.parseObject(str, Feature.SupportNonPublicField);
    }
}
