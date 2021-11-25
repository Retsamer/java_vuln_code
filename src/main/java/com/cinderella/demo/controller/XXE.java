package com.cinderella.demo.controller;

import com.google.gson.Gson;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cinderella.demo.util.xmlprint.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Cinderella
 * @time 2021/8/24 15:54
 * @Description
 **/
@RestController
@RequestMapping("/xxe")
public class XXE {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/vuln01")
    public String dom4jVuln(@RequestBody String body){
        SAXReader reader = new SAXReader();
        Document document = null;
        try{
            logger.info(body);
            document = reader.read(new InputSource(new StringReader(body)));
            Element root = document.getRootElement();
            String result = dom4jPrint.dom4j(root);
            return result;
        }catch (Exception e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }

    @PostMapping("/vuln02")
    public String domVuln(@RequestBody String body){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputStream xmlInputStream = new ByteArrayInputStream(body.getBytes());
            org.w3c.dom.Document doc = dBuilder.parse(xmlInputStream);
            doc.getDocumentElement().normalize();
            if(doc.hasChildNodes()){
                return domPrint.printDom(doc.getChildNodes());
            }else {
                Map<String,String> map = new HashMap<>();
                map.put("result","doc don't have childnode!");
                Gson gson = new Gson();
                return gson.toJson(map);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }

    @PostMapping("/vuln03")
    public String jdomVuln(@RequestBody String body){
        SAXBuilder saxBuilder = new SAXBuilder();
        StringBuilder sb = new StringBuilder();
        try{
            InputStream xmlInputStream = new ByteArrayInputStream(body.getBytes());
            org.jdom2.Document build = saxBuilder.build(xmlInputStream);
            org.jdom2.Element rootElement = build.getRootElement();
            List<org.jdom2.Element> list = rootElement.getChildren();
            for(org.jdom2.Element root : list){
                sb.append(root.getValue()+"\n");
            }
            Map<String,String> map = new HashMap<>();
            map.put("result",sb.toString());
            Gson gson = new Gson();
            return gson.toJson(map);
        }catch (IOException| JDOMException e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }

    @PostMapping("/vuln04")
    public String saxVuln(@RequestBody String body){
        try {
            InputStream xmlInputStream = new ByteArrayInputStream(body.getBytes());
            SAXParserFactory spf = SAXParserFactory.newInstance();
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            SAXParser saxParser = spf.newSAXParser();
            saxPrint output = new saxPrint();
            saxParser.parse(xmlInputStream,output);
            map.put("result",output.sb.toString());
            return gson.toJson(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }

    @PostMapping("/vuln05")
    public String staxVuln(@RequestBody String body){
        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream xmlInputStream = new ByteArrayInputStream(body.getBytes());
        try{
            XMLStreamReader streamReader = factory.createXMLStreamReader(xmlInputStream);
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result",staxPrint.printStax(streamReader));
            return gson.toJson(map);
        }catch (XMLStreamException e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }

    /**
     * XXE fix demo,https://cheatsheetseries.owasp.org/cheatsheets/XML_External_Entity_Prevention_Cheat_Sheet.html#Java
     * @param body
     * @return
     */
    @PostMapping("/sec01")
    public String dom4jSec(@RequestBody String body){
        SAXReader reader = new SAXReader();
        Document document = null;
        try{
            logger.info(body);
            //xxe fix code
            //This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all
            reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
            // If you can't completely disable DTDs, then at least do the following:
            reader.setFeature("http://xml.org/sax/features/external-general-entities",false);
            reader.setFeature("http://xml.org/sax/features/external-parameter-entities",false);
            // Disable external DTDs as well
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
            //xxe fix end
            document = reader.read(new InputSource(new StringReader(body)));
            Element root = document.getRootElement();
            String result = dom4jPrint.dom4j(root);
            return result;
        }catch (Exception e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }

    @PostMapping("/sec02")
    public String domSec(@RequestBody String body){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all
            // XML entity attacks are prevented
            dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
            // If you can't completely disable DTDs, then at least do the following:
            dbFactory.setFeature("http://xml.org/sax/features/external-general-entities",false);
            dbFactory.setFeature("http://xml.org/sax/features/external-parameter-entities",false);
            // Disable external DTDs as well
            dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
            //xxe fix code end
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputStream xmlInputStream = new ByteArrayInputStream(body.getBytes());
            org.w3c.dom.Document doc = dBuilder.parse(xmlInputStream);
            doc.getDocumentElement().normalize();
            if(doc.hasChildNodes()){
                return domPrint.printDom(doc.getChildNodes());
            }else {
                Map<String,String> map = new HashMap<>();
                map.put("result","doc don't have childnode!");
                Gson gson = new Gson();
                return gson.toJson(map);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }

    @PostMapping("/sec03")
    public String jdomSec(@RequestBody String body){
        SAXBuilder saxBuilder = new SAXBuilder();
        //xxe fix code start
        saxBuilder.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
        saxBuilder.setFeature("http://xml.org/sax/features/external-general-entities",false);
        saxBuilder.setFeature("http://xml.org/sax/features/external-parameter-entities",false);
        //xxe fix code end
        StringBuilder sb = new StringBuilder();
        try{
            InputStream xmlInputStream = new ByteArrayInputStream(body.getBytes());
            org.jdom2.Document build = saxBuilder.build(xmlInputStream);
            org.jdom2.Element rootElement = build.getRootElement();
            List<org.jdom2.Element> list = rootElement.getChildren();
            for(org.jdom2.Element root : list){
                sb.append(root.getValue()+"\n");
            }
            Map<String,String> map = new HashMap<>();
            map.put("result",sb.toString());
            Gson gson = new Gson();
            return gson.toJson(map);
        }catch (IOException| JDOMException e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }

    @PostMapping("/sec04")
    public String saxSec(@RequestBody String body){
        try {
            InputStream xmlInputStream = new ByteArrayInputStream(body.getBytes());
            SAXParserFactory spf = SAXParserFactory.newInstance();
            //xxe fix code start
            spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
            spf.setFeature("http://xml.org/sax/features/external-general-entities",false);
            spf.setFeature("http://xml.org/sax/features/external-parameter-entities",false);
            spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
            //xxe fix code end
            SAXParser saxParser = spf.newSAXParser();
            saxPrint output = new saxPrint();
            saxParser.parse(xmlInputStream,output);
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result",output.sb.toString());
            return gson.toJson(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }

    /**
     *https://cheatsheetseries.owasp.org/cheatsheets/XML_External_Entity_Prevention_Cheat_Sheet.html#Java
     */
    @PostMapping("/sec05")
    public String staxSec(@RequestBody String body){
        XMLInputFactory factory = XMLInputFactory.newInstance();
        //xxe fix code start
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        factory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);
        //xxe fix code end
        InputStream xmlInputStream = new ByteArrayInputStream(body.getBytes());
        try{
            XMLStreamReader streamReader = factory.createXMLStreamReader(xmlInputStream);
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result",staxPrint.printStax(streamReader));
            return gson.toJson(map);
        }catch (XMLStreamException e){
            logger.error(e.getMessage());
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map.put("result","XXE Parse error!");
            return gson.toJson(map);
        }
    }





}
