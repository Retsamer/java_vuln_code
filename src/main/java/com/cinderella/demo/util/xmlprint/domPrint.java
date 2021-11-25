package com.cinderella.demo.util.xmlprint;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cinderella
 * @time 2021/8/25 11:13
 * @Description
 **/
public class domPrint {
    private static Logger logger = LoggerFactory.getLogger(domPrint.class);
    private static StringBuilder sb = new StringBuilder();
    private static Map<String,String> map = new HashMap<>();
    private static Gson gson = new Gson();

    public static String printDom(NodeList nodeList){
        logger.info("[+]start printDom!");
        for(int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                sb.append(node.getNodeName()+":"+node.getTextContent()+"\n");
//                map.put(node.getNodeName(),node.getTextContent());
//                System.out.println("<"+node.getNodeName()+">");
//                System.out.println(node.getTextContent());
                if(node.hasAttributes()){
                    NamedNodeMap nodeMap = node.getAttributes();
                    for(int j = 0; j < nodeMap.getLength(); j++){
                        Node nd = nodeMap.item(j);
                        sb.append(nd.getNodeName()+":"+nd.getNodeValue()+"\n");
                        //map.put(nd.getNodeName(),nd.getNodeValue());
//                        System.out.println(nd.getNodeName()+"="+nd.getNodeValue());
                    }
                }
                if(node.hasChildNodes()){
                    printDom(node.getChildNodes());
                }
//                System.out.println("<"+node.getNodeName()+">");
            }
        }
        map.put("result",sb.toString());
        String str = gson.toJson(map);
        logger.info("[+]result is "+str);
        return str;
    }
}
