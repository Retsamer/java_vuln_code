package com.cinderella.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Cinderella
 * @time 2021/8/31 09:37
 * @Description
 **/

/**
 * 修复方案：升级thymeleaf到3.0.12
 */
@Controller
@RequestMapping("/thymeleafi")
public class Thymeleafi {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/vuln01")
    public String vuln01(@RequestParam String str) {
        return str;
    }

    @GetMapping("/vuln02")
    public String vuln02(@RequestParam String str){
        return "welcome::"+str;
    }
}
