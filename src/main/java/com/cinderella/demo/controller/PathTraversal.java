package com.cinderella.demo.controller;

import com.cinderella.demo.util.SecurityUtil;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cinderella
 * @time 2021/8/30 10:45
 * @Description
 **/
@RestController
@RequestMapping("/pathtraversal")
public class PathTraversal {

    /**
     * payload：http://127.0.0.1:8000/pathtraversal/filereadvuln?dir=/../../../../../etc/passwd
     * @param dir
     * @return
     * @throws IOException
     */
    @RequestMapping("/filereadvuln")
    public String fileReadVuln(@RequestParam("str") String dir) throws IOException{
        StringBuilder sb = new StringBuilder();
        String finaldir = System.getProperty("user.dir") +"/"+ dir;
        System.out.println(finaldir);
        File file = new File(finaldir);
        FileInputStream in = new FileInputStream(file);
        int tempbyte;
        while((tempbyte =in.read())!=-1){
            sb.append((char)tempbyte);
        }
        in.close();
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("result",sb.toString());
        return gson.toJson(map);
    }

    /**
     * payload:http://127.0.0.1:8000/pathtraversal/pathvuln?dir=/../../../../../etc/
     * @param dir
     * @return
     */
    @RequestMapping("/pathvuln")
    public String pathtraversalVuln(@RequestParam("str") String dir){
        StringBuilder sb = new StringBuilder();
        String finaldir = System.getProperty("user.dir") + dir;
        System.out.println(finaldir);
        String[] files = new File(dir).list();
        for(String file:files){
            sb.append(file+"\n");
        }
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("result",sb.toString());
        return gson.toJson(map);
    }

    @RequestMapping("/filereadsec")
    public String fileReadSec(@RequestParam("str") String dir) throws IOException{
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        if (SecurityUtil.pathFilter(dir) == null) {
            map.put("result","Illegal file path!");
            return gson.toJson(map);
        }
        StringBuilder sb = new StringBuilder();
        String finaldir = System.getProperty("user.dir") +"/"+ dir;
        System.out.println(finaldir);
        File file = new File(finaldir);
        FileInputStream in = new FileInputStream(file);
        int tempbyte;
        while((tempbyte =in.read())!=-1){
            sb.append((char)tempbyte);
        }
        in.close();
        map.put("result",sb.toString());
        return gson.toJson(map);
    }

    @RequestMapping("/pathsec")
    public String pathtraversalSec(@RequestParam("str") String dir){
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        if (SecurityUtil.pathFilter(dir) == null) {
            map.put("result","Illegal file path!");
            return gson.toJson(map);
        }
        StringBuilder sb = new StringBuilder();
        String finaldir = System.getProperty("user.dir") + dir;
        System.out.println(finaldir);
        String[] files = new File(dir).list();
        for(String file:files){
            sb.append(file+"\n");
        }
        map.put("result",sb.toString());
        return gson.toJson(map);
    }
}
