package com.cinderella.demo.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

/**
 * @author Cinderella
 * @time 2021/8/30 14:27
 * @Description
 **/
@RestController
public class CommandInject {
    private static final Pattern FILTER_PATTERN = Pattern.compile("[0-9A-Za-z_/]+");

    /**
     * 拼接执行任意命令
     * @param dir
     * @return
     * @throws IOException
     */
    @RequestMapping("/cmdi/vuln01")
    public String commandinject01(@RequestParam("str") String dir) throws IOException {
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        String[] cmdList= new String[]{"sh","-c","ls -lh"+dir};
        ProcessBuilder builder = new ProcessBuilder(cmdList);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        map.put("result",IOUtils.toString(process.getInputStream(),"UTF-8"));
        return gson.toJson(map);
    }

    /**
     * 提前对用户输入参数进行限制
     * @param dir
     * @return
     * @throws IOException
     */
    @RequestMapping("/cmdi/sec01")
    public String commandsec02(@RequestParam("str") String dir) throws IOException {
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        if (!FILTER_PATTERN.matcher(dir).matches()) {
            map.put("result","dir is illgal!");
            return gson.toJson(map);
        }
        String[] cmdList= new String[]{"sh","-c","ls -lh",dir};
        ProcessBuilder builder = new ProcessBuilder(cmdList);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        map.put("result",IOUtils.toString(process.getInputStream(),"UTF-8"));
        return gson.toJson(map);
    }

}
