package com.cinderella.demo.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Cinderella
 * @time 2021/9/1 15:50
 * @Description
 **/
@Configuration
public class KaptchaConfig {
    @Bean(name="captchaProducer")
    public DefaultKaptcha getKaptchaBean(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        //验证码字符范围
        properties.setProperty("kaptcha.textproducer.char.string", "1234567890");
        //图片边框颜色
        properties.setProperty("kaptcha.border.color", "245,248,249");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "2");
        //图片宽度
        properties.setProperty("kaptcha.image.width", "100");
        //图片高度
        properties.setProperty("kaptcha.image.height", "35");
        //字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //session的key
        //properties.setProperty("kaptcha.session.key", "code");
        //长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
