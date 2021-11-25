package com.cinderella.demo.controller;

import com.cinderella.demo.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Cinderella
 * @time 2021/9/1 17:05
 * @Description
 **/
@Controller
@RequestMapping("/urlRedirect")
public class URLRedirect {

    @GetMapping("/vuln01")
    public String redirect(@RequestParam("str") String url){
        return "redirect:" + url;
    }

    @GetMapping("/vuln02")
    @ResponseBody
    public static void sendRedirectVuln(@RequestParam("str") String url,HttpServletResponse response) throws IOException {
        response.sendRedirect(url); // 302 redirect
    }

    @GetMapping("/sec01")
    @ResponseBody
    public static void sendRedirectSec(@RequestParam("str") String url, HttpServletResponse response) throws IOException {

        if(SecurityUtil.checkURL(url)==null){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("url forbidden");
            return;
        }
        response.sendRedirect(url); // 302 redirect
    }

    @GetMapping("/sec02")
    @ResponseBody
    public static void forward(@RequestParam("str") String url,HttpServletRequest req,HttpServletResponse response){
        RequestDispatcher rd = req.getRequestDispatcher(url);
        try{
            rd.forward(req,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
