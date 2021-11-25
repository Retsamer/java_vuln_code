package com.cinderella.demo.util.xmlprint;

import com.google.gson.Gson;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cinderella
 * @time 2021/8/25 10:52
 * @Description
 **/
public class dom4jPrint {
    private static Logger logger = LoggerFactory.getLogger(dom4jPrint.class);

    public static String dom4j(Element root) throws Exception{
        logger.info("[+] start xml parse!");
        StringBuilder sb = new StringBuilder();
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        List<Element> list = root.elements();
        for(Element child : list){
            List<Element> objs = child.elements();
            for(Element obj : objs){
                sb.append(obj.getName()+":"+obj.getText()+"\n");
                //map.put(obj.getName(),obj.getText());
            }
        }
        map.put("result",sb.toString());
        String str = gson.toJson(map);
        logger.info("[+] result is" + str);
        return str;
    }
}
