package com.cinderella.demo.util.xmlprint;

import com.google.gson.Gson;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cinderella
 * @time 2021/8/25 11:54
 * @Description
 **/
public class saxPrint extends DefaultHandler {
    public static StringBuilder sb = new StringBuilder();
//    void print(Object... objs) {
//        for (Object obj : objs) {
//            sb.append(obj);
//            sb.append(" ");
//        }
//        sb.append("\n");
//    }

    @Override
    public void startDocument() throws SAXException {
        //sb.append("start document");
    }

    @Override
    public void endDocument() throws SAXException {
        //sb.append("end document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        sb.append(localName+qName+"\n");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        sb.append(localName+qName+"\n");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(new String(ch, start, length)+"\n");
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        sb.append("error:"+e);
    }

    public String result(){
        return sb.toString();
    }
}
