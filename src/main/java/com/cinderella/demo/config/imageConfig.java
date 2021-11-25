package com.cinderella.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author Cinderella
 * @time 2021/9/22 14:55
 * @Description
 **/
@Configuration
public class imageConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+"/tmp/upload/");
    }
}
