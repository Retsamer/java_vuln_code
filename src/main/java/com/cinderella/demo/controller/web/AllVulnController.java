package com.cinderella.demo.controller.web;

import com.cinderella.demo.dao.Vuln;
import com.cinderella.demo.dao.VulnPath;
import com.cinderella.demo.dao.VulnRepaire;
import com.cinderella.demo.mapper.VulnMapper;
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
 * @time 2021/9/3 10:25
 * @Description
 **/
@RestController
public class AllVulnController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VulnMapper vulnMapper;

    @Autowired
    private VulnPathMapper vulnPathMapper;

    @Autowired
    private VulnRepaireMapper vulnRepaireMapper;

    @GetMapping("/allVuln")
    public String selectAllVuln(){
        logger.info("[+] start select all vuln!");
        List<Vuln> list = vulnMapper.findAllVuln();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
