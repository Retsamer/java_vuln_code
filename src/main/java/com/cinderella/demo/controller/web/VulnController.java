package com.cinderella.demo.controller.web;

import com.cinderella.demo.dao.VulnPath;
import com.cinderella.demo.dao.VulnRepaire;
import com.cinderella.demo.mapper.VulnMapper;
import com.cinderella.demo.mapper.VulnPathMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Cinderella
 * @time 2021/9/16 11:04
 * @Description
 **/
@RestController
public class VulnController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VulnPathMapper vulnPathMapper;

    @GetMapping("/SQLVuln")
    public String SQLVuln(){
        logger.info("[+] start check sql vuln");
        List<VulnPath> res = vulnPathMapper.findAllSQLVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/XSSVuln")
    public String XSSVuln(){
        logger.info("[+] start check xss vuln");
        List<VulnPath> res = vulnPathMapper.findAllXSSVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/URLVuln")
    public String URLVuln(){
        logger.info("[+] start check urlredirect vuln");
        List<VulnPath> res = vulnPathMapper.findAllURLVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/ThymeleafVuln")
    public String ThymeleafVuln(){
        logger.info("[+] start check Thymeleaf vuln");
        List<VulnPath> res = vulnPathMapper.findAllThymeleafVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/XXEVuln")
    public String XXEVuln(){
        logger.info("[+] start check XXE vuln");
        List<VulnPath> res = vulnPathMapper.findAllXXEVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/SSRFVuln")
    public String SSRFVuln(){
        logger.info("[+] start check SSRF vuln");
        List<VulnPath> res = vulnPathMapper.findAllSSRFVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/PathtraversalVuln")
    public String PathtraversalVuln(){
        logger.info("[+] start check Pathtraversal vuln");
        List<VulnPath> res = vulnPathMapper.findAllPathtraversalVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/IDORVuln")
    public String IDORVuln(){
        logger.info("[+] start check IDOR vuln");
        List<VulnPath> res = vulnPathMapper.findAllIdorVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/UploadVuln")
    public String UploadVuln(){
        logger.info("[+] start check UPLOAD vuln");
        List<VulnPath> res = vulnPathMapper.findAllUploadVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/FastjsonVuln")
    public String FastjsonVuln(){
        logger.info("[+] start check Fastjson vuln");
        List<VulnPath> res = vulnPathMapper.findAllFastjsonVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/CmdiVuln")
    public String CmdiVuln(){
        logger.info("[+] start check Cmdi vuln");
        List<VulnPath> res = vulnPathMapper.findAllCmdiVuln01();
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GetMapping("/SHIROVuln")
    public String ShiroVuln(){
        logger.info("[+] start check Shiro vuln");
        List<VulnPath> res = vulnPathMapper.findAllShiroVuln01();
        Gson gson  = new Gson();
        return gson.toJson(res);
    }
}
