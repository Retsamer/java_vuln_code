package com.cinderella.demo.util.xmlprint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Cinderella
 * @time 2021/8/25 14:24
 * @Description
 **/
public class staxPrint {
    private static Logger logger = LoggerFactory.getLogger(staxPrint.class);


    public static String printStax(XMLStreamReader streamReader) {
        StringBuilder sb = new StringBuilder();
        try {
            while (streamReader.hasNext()) {//reader.hasNext();说明文件未到结尾。
                int eventId = streamReader.next();
                switch (eventId) {
                    case XMLStreamConstants.START_DOCUMENT:
                        sb.append("start docmuent \n");
                        break;

                    case XMLStreamConstants.START_ELEMENT:
                        System.out.println("<" + streamReader.getLocalName() + ">");
                        for (int i = 0; i < streamReader.getAttributeCount(); i++) {
                            sb.append(streamReader.getAttributeLocalName(i) + "=" + streamReader.getAttributeValue(i)+"\n");
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        if (streamReader.isWhiteSpace()) {
                            break;
                        }
                        sb.append(streamReader.getText()+"\n");
                        break;
                    case XMLStreamConstants.END_ELEMENT:

                        sb.append("</" + streamReader.getLocalName() + ">"+"\n");

                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        sb.append("end docmuent\n");
                        break;
                    default:
                        break;
                }
            }
            logger.info("[+] result is \n"+sb.toString());
            return sb.toString();
        }catch (XMLStreamException e){
            logger.error(e.getMessage());
            return "XXE Parse error!";
        }
    }
}
