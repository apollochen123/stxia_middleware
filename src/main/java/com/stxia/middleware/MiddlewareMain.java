
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

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
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
        String bodyValTemplate = "method=hwa.hvalve.close&meter_sn=52000002&app_sn=&7573f4927e5ee92e&extend=hwa&user_token=9cab82811914830847007c9cf30670a2";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<String>(bodyValTemplate, headers);
        ResponseEntity<String> a = restTemplate.exchange("http://47.93.227.123/", HttpMethod.POST, entity, String.class);
        JSONObject j = JSONObject.parseObject(a.getBody());
        System.out.println(j.get("data_value"));
    }
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MyMappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
