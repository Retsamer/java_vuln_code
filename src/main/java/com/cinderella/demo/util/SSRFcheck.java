package com.cinderella.demo.util;

import com.cinderella.demo.config.WebConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.URI;
import java.util.ArrayList;
import org.apache.commons.net.util.SubnetUtils;

/**
 * @author Cinderella
 * @time 2021/8/30 11:27
 * @Description
 **/
public class SSRFcheck {
    public static  Logger logger = LoggerFactory.getLogger(SSRFcheck.class);

    /**
     * 检测白名单，最推荐的方式
     * @param url
     * @return
     */
    public static boolean checkURLFckSSRF(String url){
        if(null == url){
            return false;
        }
        ArrayList<String> ssrfSafeDomains = WebConfig.getSsrfSafeDomains();
        logger.info("[+] ssrf safe domain is"+ssrfSafeDomains.toString());
        try{
            String host= SecurityUtil.gethost(url);
            if(!SecurityUtil.isHttp(url)){
                return false;
            }
            if(ssrfSafeDomains.contains(host)){
                return true;
            }
            for(String ssrfSafeDomain:ssrfSafeDomains){
                if(host.endsWith("."+ssrfSafeDomain)){
                    return true;
                }
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    /**
     * 判断一个URL的IP是否是内网IP
     * @param url
     * @return
     */
    public static boolean isInternalIpByUrl(String url){
        String host = url2host(url);
        if(host.equals("")){
            return true;
        }
        String ip = host2ip(host);
        if(ip.equals("")){
            return true;
        }
        return isInternalIp(ip);
    }

    /**
     * 使用SubnetUtils库判断ip是否在内网网段。
     * @param strIP ip字符串
     * @return 如果为内网ip，返回true，否则返回false
     */
    public static boolean isInternalIp(String strIP){
        if(!StringUtils.hasLength(strIP)){
            return true;
        }
        ArrayList<String> blackSubnets = WebConfig.getSsrfBlockIps();
        for(String subnet : blackSubnets){
            SubnetUtils utils = new SubnetUtils(subnet);
            if(utils.getInfo().isInRange(strIP)){
                return true;
            }
        }
        return false;
    }


    /**
     * HOST->IP
     * @param host 域名host
     * 会将各种进制的ip转为正常ip
     * 167772161转换为10.0.0.1
     * 127.0.0.1.xip.io转换为127.0.0.1
     * @return
     */
    private static String host2ip(String host){
        try{
            InetAddress IpAddress = InetAddress.getByName(host);
            return IpAddress.getHostAddress();
        }catch (Exception e){
            return "";
        }
    }

    /**
     * URL->host,限制协议为http/https
     * @param url
     * @return
     */
    private static String url2host(String url){
        try{
            //使用URI，防止net.URL出现解析漏洞,such as:http://www.google.com@baidu.com
            URI u = new URI(url);
            if(SecurityUtil.isHttp(url)){
                return u.getHost();
            }
            return "";
        }catch (Exception e){
            return "";
        }
    }
}
