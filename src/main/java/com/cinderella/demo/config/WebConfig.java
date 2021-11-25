package com.cinderella.demo.config;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author Cinderella
 * @time 2021/8/30 11:22
 * @Description
 **/
@Component
public class WebConfig {
    private static ArrayList<String> safeDomains = new ArrayList<>();
    private static ArrayList<String> blockDomains = new ArrayList<>();
    private static ArrayList<String> ssrfSafeDomains = new ArrayList<>();
    private static ArrayList<String> ssrfBlockDomains = new ArrayList<>();
    private static ArrayList<String> ssrfBlockIps = new ArrayList<>();


    public static void setSafeDomains(ArrayList<String> safeDomains) {
        WebConfig.safeDomains = safeDomains;
    }

    public static ArrayList<String> getSafeDomains() {
        return safeDomains;
    }


    public static void setBlockDomains(ArrayList<String> blockDomains) {
        WebConfig.blockDomains = blockDomains;
    }

    public static ArrayList<String> getBlockDomains() {
        return blockDomains;
    }


    public static  void setSsrfSafeDomains(ArrayList<String> ssrfSafeDomains) {
        WebConfig.ssrfSafeDomains = ssrfSafeDomains;
    }

    public static ArrayList<String> getSsrfSafeDomains() {
        return ssrfSafeDomains;
    }


    public static void setSsrfBlockDomains(ArrayList<String> ssrfBlockDomains) {
        WebConfig.ssrfBlockDomains = ssrfBlockDomains;
    }

    public static ArrayList<String> getSsrfBlockDomainsDomains() {
        return ssrfBlockDomains;
    }


    public static void setSsrfBlockIps(ArrayList<String> ssrfBlockIps) {
        WebConfig.ssrfBlockIps = ssrfBlockIps;
    }

    public static ArrayList<String> getSsrfBlockIps() {
        return ssrfBlockIps;
    }
}
