
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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.stxia.middleware.configuration.MyMappingJackson2HttpMessageConverter;
import com.stxia.middleware.upload.UploadData;


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
    private static Logger LOG = Logger.getLogger(UploadData.class);
    public static void main(String[] args)
    {
//        SpringApplication.run(MiddlewareMain.class, args);
//        WaterMeterSwitch wms = new WaterMeterSwitch();
//        wms.swi();
        RestTemplate restTemplate  = new RestTemplate();
        Map<String,String> map = new HashMap<>();
        map.put("method", "hwa.hvalve.close");
        map.put("meter_sn", "52000002");
        map.put("app_sn", "7573f4927e5ee92e");
        map.put("extend", "hwa");
        map.put("user_token", "9cab82811914830847007c9cf30670a2");
        
        String a  = restTemplate.postForObject("http://47.93.227.123", null, String.class, map);
        System.out.println(a);
    }
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MyMappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
