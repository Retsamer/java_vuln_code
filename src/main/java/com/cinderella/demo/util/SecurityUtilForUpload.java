package com.cinderella.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Cinderella
 * @time 2021/8/30 10:16
 * @Description
 **/
public class SecurityUtilForUpload {

    @Value("${file.upload.path}")
    private static String path;

    /**
     * Suffix must in whiteList
     * @param Suffix
     * @return
     */
    public  static boolean WhiteList(String Suffix){
        String picSuffixList[] = {".jpg", ".png", ".jpeg", ".gif", ".bmp", ".ico"};
        for (String white_suffix : picSuffixList) {
            if (Suffix.toLowerCase().equals(white_suffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是image文件
     * @param file
     * @return
     * @throws IOException
     */
    public  static boolean isImage(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        System.out.println(bi);
        return bi == null;
    }

    public static  File convert(MultipartFile multiFile) throws Exception {
        String fileName = multiFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        UUID uuid = Generators.timeBasedGenerator().generate();

        File convFile = new File(path + uuid + suffix);
        boolean ret = convFile.createNewFile();
        if (!ret) {
            return null;
        }
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multiFile.getBytes());
        fos.close();
        return convFile;
    }

    public  static boolean MiME(String mimeType){
        String mimeTypeBlackList[] = {
                "text/html",
                "text/javascript",
                "application/javascript",
                "application/ecmascript",
                "text/xml",
                "application/xml"
        };
        for (String blackMimeType : mimeTypeBlackList) {
            // 用contains是为了防止text/html;charset=UTF-8绕过
            if (replaceSpecialStr(mimeType).toLowerCase().contains(blackMimeType)) {
                return false;
            }
        }
        return true;
    }

    private static String replaceSpecialStr(String str) {
        StringBuilder sb = new StringBuilder();
        str = str.toLowerCase();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // 如果是0-9
            if (ch >= 48 && ch <= 57 ){
                sb.append(ch);
            }
            // 如果是a-z
            else if(ch >= 97 && ch <= 122) {
                sb.append(ch);
            }
            else if(ch == '/' || ch == '.' || ch == '-'){
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
