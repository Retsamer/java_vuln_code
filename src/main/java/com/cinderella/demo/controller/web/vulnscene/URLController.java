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
 * @time 2021/9/16 15:46
 * @Description
 **/
@RestController
public class URLController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VulnPathMapper vulnPathMapper;

    @Autowired
    private VulnRepaireMapper vulnRepaireMapper;

    @GetMapping("/url01")
    public String URL01(){
        logger.info("[+] start check url01 vuln and repaire");
        String str = "urlredirect01";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        List<VulnRepaire> vulnRepaire = vulnRepaireMapper.findURLRRepaireByUrl();
        vulnPath.setVulnRepaire(vulnRepaire);
        return gson.toJson(vulnPath);
    }

    @GetMapping("/url02")
    public String URL02(){
        logger.info("[+] start check url02 vuln and repaire");
        String str = "urlredirect02";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        List<VulnRepaire> vulnRepaire = vulnRepaireMapper.findURLRRepaireByUrl();
        vulnPath.setVulnRepaire(vulnRepaire);
        return gson.toJson(vulnPath);
    }
}
