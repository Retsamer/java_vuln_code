package com.cinderella.demo.controller.web.vulnscene;

import com.cinderella.demo.dao.VulnPath;
import com.cinderella.demo.dao.VulnRepaire;
import com.cinderella.demo.mapper.VulnPathMapper;
import com.cinderella.demo.mapper.VulnRepaireMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Cinderella
 * @time 2021/9/16 15:39
 * @Description
 **/
@RestController
public class SQLiController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VulnPathMapper vulnPathMapper;

    @Autowired
    private VulnRepaireMapper vulnRepaireMapper;

    @GetMapping("/sqli01")
    public String Sqli01(){
        logger.info("[+] start check sqli01 vuln and repaire");
        String str = "sqli01";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }

    @GetMapping("/sqli02")
    public String Sqli02(){
        logger.info("[+] start check sqli02 vuln and repaire");
        String str = "sqli02";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }

    @GetMapping("/sqli03")
    public String Sqli03(){
        logger.info("[+] start check sqli03 vuln and repaire");
        String str = "sqli03";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }

    @GetMapping("/sqli04")
    public String Sqli04(){
        logger.info("[+] start check sqli04 vuln and repaire");
        String str = "sqli04";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }

}
