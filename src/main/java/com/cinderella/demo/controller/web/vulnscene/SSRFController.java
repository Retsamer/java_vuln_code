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

import java.util.List;

/**
 * @author Cinderella
 * @time 2021/9/16 16:01
 * @Description
 **/
@RestController
public class SSRFController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VulnPathMapper vulnPathMapper;

    @Autowired
    private VulnRepaireMapper vulnRepaireMapper;

    @GetMapping("/ssrf01")
    public String SSRF(){
        logger.info("[+] start check ssrf vuln and repaire");
        String str = "ssrf";
        Gson gson = new Gson();
        VulnPath vulnPath = vulnPathMapper.findVuln(str);
        List<VulnRepaire> vulnRepaire = vulnRepaireMapper.findSSRFRepaireByUrl();
        vulnPath.setVulnRepaire(vulnRepaire);
        return gson.toJson(vulnPath);
    }
}
