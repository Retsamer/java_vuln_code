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

import java.util.LinkedList;
import java.util.List;

/**
 * @author Cinderella
 * @time 2021/9/16 15:58
 * @Description
 **/
@RestController
public class XXEController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VulnPathMapper vulnPathMapper;

    @Autowired
    private VulnRepaireMapper vulnRepaireMapper;

    @GetMapping("/xxe01")
    public String XXE01(){
        logger.info("[+] start check xxe01 vuln and repaire");
        String str = "xxe01";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }

    @GetMapping("/xxe02")
    public String XXE02(){
        logger.info("[+] start check xxe02 vuln and repaire");
        String str = "xxe02";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }

    @GetMapping("/xxe03")
    public String XXE03(){
        logger.info("[+] start check xxe03 vuln and repaire");
        String str = "xxe03";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }

    @GetMapping("/xxe04")
    public String XXE04(){
        logger.info("[+] start check xxe04 vuln and repaire");
        String str = "xxe04";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }

    @GetMapping("/xxe05")
    public String XXE05(){
        logger.info("[+] start check xxe05 vuln and repaire");
        String str = "xxe05";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }
}
