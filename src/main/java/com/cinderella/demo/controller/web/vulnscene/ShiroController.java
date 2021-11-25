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
 * @time 2021/9/22 13:31
 * @Description
 **/
@RestController
public class ShiroController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VulnPathMapper vulnPathMapper;

    @Autowired
    private VulnRepaireMapper vulnRepaireMapper;

    @GetMapping("/shiro")
    public String Shiro(){
        logger.info("[+]start check shiro !");
        String str = "shiro";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        VulnRepaire vulnRepaire = vulnRepaireMapper.findRepaireBySymbol(str);
        List<VulnRepaire> tmp = new LinkedList<>();
        tmp.add(vulnRepaire);
        vulnPath.setVulnRepaire(tmp);
        return gson.toJson(vulnPath);
    }
}
