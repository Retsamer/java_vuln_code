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
 * @time 2021/9/16 16:03
 * @Description
 **/
@RestController
public class PathraversalController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VulnPathMapper vulnPathMapper;

    @Autowired
    private VulnRepaireMapper vulnRepaireMapper;

    @GetMapping("/fileread")
    public String Pathraversal01(){
        logger.info("[+] start check Pathraversal-fileread vuln and repaire");
        String str = "pathtraversal01";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }

    @GetMapping("/path")
    public String Pathraversal02(){
        logger.info("[+] start check Pathraversal-path vuln and repaire");
        String str = "pathtraversal02";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }
}
