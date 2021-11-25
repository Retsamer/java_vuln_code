package com.cinderella.demo.config;

import com.cinderella.demo.util.SafeDomainParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cinderella
 * @time 2021/8/30 11:16
 * @Description
 **/
@Configuration
public class SafeDomainConfig {
    @Bean
    public SafeDomainParser safeDomainParser(){
        try{
            return new SafeDomainParser();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
