
/**
 * Project Name:com.stxia.middleware 
 * File Name:MiddlewareMain.java <br/><br/>  
 * Description: MiddlewareMain
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Nov 21, 2017 3:11:15 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.stxia.middleware.configuration.MyMappingJackson2HttpMessageConverter;

/**
 * ClassName: MiddlewareMain <br/>
 * <br/>
 * Description: MiddlewareMain
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
@SpringBootApplication
@EnableScheduling
public class MiddlewareMain
{
    public static void main(String[] args)
    {
        SpringApplication.run(MiddlewareMain.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MyMappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
