
  
 /**
  * Project Name:com.stxia.middleware 
  * File Name:MappingJackson2HttpMessageConverter.java <br/><br/>  
  * Description: MappingJackson2HttpMessageConverter
  * Copyright: Copyright (c) 2017 
  * Company:SAP
  * 
  * @author SAP
  * @date Nov 23, 2017 2:11:20 PM
  * @version 
  * @see
  * @since 
  */
  package com.stxia.middleware.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
  * ClassName: MappingJackson2HttpMessageConverter <br/><br/> 
  * Description: MappingJackson2HttpMessageConverter
  * @author SAP
  * @version 
  * @see
  * @since 
  */
public class MyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter 
{
    public MyMappingJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.APPLICATION_JSON);
        setSupportedMediaTypes(mediaTypes);// tag6
    }

}
 