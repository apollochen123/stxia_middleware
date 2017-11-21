
/**
 * Project Name:com.stxia.middleware 
 * File Name:UploadData.java <br/><br/>  
 * Description: TODO
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Nov 21, 2017 3:19:37 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.upload;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: UploadData <br/>
 * <br/>
 * Description: TODO
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
@Component
public class UploadData
{
    @Autowired
    RestTemplate restTemplate;

    private static Logger LOG = Logger.getLogger(UploadData.class);
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //第一次延迟10秒执行，当执行完后每2秒再执行，都是单线程执行
    @Scheduled(initialDelay = 10000, fixedDelay = 2000)
    public void timerInit()
    {
        LOG.info("Thread:" + Thread.currentThread().getName() + "init : " + dateFormat.format(System.currentTimeMillis()));
    }

    @Scheduled(initialDelay = 9000, fixedDelay = 3000)
    public void timerInit2()
    {
        LOG.info("Thread:" + Thread.currentThread().getName() + "init2 : " + dateFormat.format(System.currentTimeMillis()));
    }
    
    

}
