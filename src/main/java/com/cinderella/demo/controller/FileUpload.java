package com.cinderella.demo.controller;

import com.cinderella.demo.util.SecurityUtilForUpload;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Cinderella
 * @time 2021/8/25 15:48
 * @Description
 **/
@Controller
public class FileUpload {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${file.upload.path}")
    private String path;

    @PostMapping("/upload/vuln")
    @ResponseBody
    public String FileUploadVuln(@RequestPart MultipartFile file) {
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        if(file.isEmpty()){
            map.put("result","File Can't be empty!");
            return gson.toJson(map);
        }
        try {
            createDir();
            CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
            String filename = file.getOriginalFilename();
            String filepath = path+filename;
            File dest = new File(filepath);
            Files.copy(file.getInputStream(), dest.toPath(),options);
            String fileloadUri = "/images/" + filename;
            System.out.println(fileloadUri);
            map.put("code","200");
            map.put("result","Upload success! upload dir is "+fileloadUri);
            return gson.toJson(map);
        }catch (IOException e){
            logger.error("[-] File Upload Failed！");
            map.put("code","400");
            map.put("result","File Upload Failed!");
            return gson.toJson(map);
        }
    }

    @PostMapping("/upload/picture")
    @ResponseBody
    public String uploadPic(@RequestPart MultipartFile file) throws Exception{
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        if(file.isEmpty()){
            map.put("code","400");
            map.put("result","Upload file is empty");
            return gson.toJson(map);
        }
        String fileName = file.getOriginalFilename();
        String Suffix = fileName.substring(fileName.lastIndexOf("."));
        String mimeType = file.getContentType();
        logger.info(String.format("[+] pciture suffix is %s,mimeType is %s",Suffix,mimeType));
        File excelFile = SecurityUtilForUpload.convert(file);
        if(!SecurityUtilForUpload.WhiteList(Suffix)){
            map.put("code","400");
            map.put("result","Upload failed. Illeagl picture!");
            return gson.toJson(map);
        }

        if(!SecurityUtilForUpload.MiME(mimeType)){
            map.put("code","400");
            map.put("result","Upload failed. Illeagl Mime!");
            return gson.toJson(map);
        }

        if(SecurityUtilForUpload.isImage(excelFile)){
            map.put("code","400");
            map.put("result","Upload failed. Illeagl picture.pic");
            return gson.toJson(map);
        }

        try {
            createDir();
            String filename = file.getOriginalFilename();
            String filepath = path+filename;
            File dest = new File(filepath);
            Files.copy(file.getInputStream(), dest.toPath());
            String fileloadUri = "/images/" + filename;
            System.out.println(fileloadUri);
            map.put("code","200");
            map.put("result","Upload success! upload dir is "+fileloadUri);
            return gson.toJson(map);
        }catch (IOException e){
            e.printStackTrace();
            map.put("code","400");
            map.put("result","Upload Failed!");
            return gson.toJson(map);
        }
    }

    public void createDir(){
        File dir = new File(path);
        if(dir.exists() == true){
            logger.info("[+] 目录已经存在");
        }else{
            dir.mkdirs();
            logger.info("[+] 目录创建成功");
        }
    }

}
