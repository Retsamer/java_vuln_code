package com.cinderella.demo.util;

import com.cinderella.demo.config.WebConfig;
import com.cinderella.demo.util.ssrfHook.SocketHook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;

/**
 * @author Cinderella
 * @time 2021/8/30 10:45
 * @Description
 **/
public class SecurityUtil {
    /**
     * filter .. and /
     * @param filepath
     * @return
     */
    public static String pathFilter(String filepath) {
        String temp = filepath;

        // use while to sovle multi urlencode
        while (temp.indexOf('%') != -1) {
            try {
                temp = URLDecoder.decode(temp, "utf-8");
            } catch (UnsupportedEncodingException e) {
                return "Unsupported encoding exception: " + filepath;
            } catch (Exception e) {
                return e.toString();
            }
        }

        if (temp.contains("..") || temp.charAt(0) == '/') {
            return null;
        }

        return filepath;
    }

    /**
     * 基于黑白名单进行检测，优先判断黑名单，性能有问题
     * @param url
     * @return
     */
    public static String checkURL(String url){
        if(null == url){
            return null;
        }

        ArrayList<String> safeDomains = WebConfig.getSafeDomains();
        ArrayList<String> blockDomains = WebConfig.getBlockDomains();
        try{
            String host = gethost(url);

            if(!isHttp(url)){
                return null;
            }

            if (blockDomains.contains(host)){
                return null;
            }
            for(String blockDomain:blockDomains){
                if(host.endsWith("."+blockDomain)){
                    return null;
                }
            }
            if(safeDomains.contains(host)){
                return null;
            }
            for(String safedomain:safeDomains){
                if(host.endsWith("."+safedomain)){
                    return url;
                }
            }
            return null;
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过白名单方式进行检测，能做到URL收敛的情况下，最推荐的方式
     * @param url
     * @return
     */
    public static boolean checkSSRFByWhiteList(String url){
        return SSRFcheck.checkURLFckSSRF(url);
    }

    /**
     *  如果不能使用白名单，建议使用这种方式，检测URL目标是否是内网进行判断，但是会有绕过风险
     *  1、TTL为0会被绕过，
     *  2、使用重定向可以绕过
     *  需要设置禁用重定向以及TTL默认不为0
     * @param url
     * @return
     */
    public static boolean checkSSRFWithoutRedirect(String url){
        if(url == null){
            return false;
        }
        return !SSRFcheck.isInternalIpByUrl(url);
    }

    /**
     * Check SSRF by hook socket。
     * @throws IOException
     */
    public static void startSSRFHook() throws IOException{
        SocketHook.startHook();
    }

    /**
     * close socket hook
     * @throws IOException
     */
    public static void stopSSRFHook(){
        SocketHook.stopHook();
    }

    /**
     * GET http url host
     * @param url
     * @return
     */
    public static String gethost(String url){
        try{
            //使用URI，防止使用URL造成解析漏洞
            URI uri = new URI(url);
            return uri.getHost().toLowerCase();
        }catch (URISyntaxException e){
            return "";
        }
    }

    /**
     * 限制目标仅使用http和https协议
     * @param url
     * @return
     */
    public static boolean isHttp(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    /**
     * URLConnection方法连接
     * @param url
     * @return
     */
    public static String URLConnection(String url) {
        try
        {
            URL u = new URL(url);
            URLConnection urlConnection = u.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String urlString = "";
            String current;
            while((current = in.readLine()) != null)
            {
                urlString += current;
            }
            in.close();
            return urlString;
        }catch(IOException e)
        {
            return e.getMessage();
        }
    }

    /**
     * HTTPURLConnection方法连接
     * @param url
     * @return
     */
    public static String HTTPURLConnection(String url){
        try
        {
            URL u = new URL(url);
            URLConnection urlConnection = u.openConnection();
            HttpURLConnection connection = null;
            if(urlConnection instanceof HttpURLConnection)
            {
                connection = (HttpURLConnection) urlConnection;
            }
            else
            {
                return "请输入 URL 地址";
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;
            while((current = in.readLine()) != null)
            {
                urlString += current;
            }
            in.close();
            return urlString;
        }catch(IOException e)
        {
            return e.getMessage();
        }
    }

}
