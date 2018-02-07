
/**
 * Project Name:com.stxia.middleware 
 * File Name:MyNotificationListener.java <br/><br/>  
 * Description: TODO
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Feb 7, 2018 4:52:08 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.watermeter.enable;

import org.kaaproject.kaa.client.notification.NotificationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.hyy.cn.NotificationPoint;
import com.stxia.middleware.configuration.KaaClientConfig;
import com.stxia.middleware.watermeter.enable.constants.WaterMeterSwitchConstants;

/**
 * ClassName: MyNotificationListener <br/>
 * <br/>
 * Description: TODO
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
public class MyNotificationListener implements NotificationListener
{
    private RestTemplate restTemplate;
    private String url;

    public MyNotificationListener(RestTemplate restTemplate, String url)
    {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    private static final Logger LOG = LoggerFactory.getLogger(MyNotificationListener.class);

    /**
     * {@inheritDoc}
     * 
     * @see org.kaaproject.kaa.client.notification.NotificationListener#onNotification(long,
     *      com.hyy.cn.NotificationPoint)
     */
    @Override
    public void onNotification(long id, NotificationPoint notification)
    {
        LOG.info("Notification from the topic with id [{}] and name [{}] received.", id, KaaClientConfig.getTopic(id).getName());
        LOG.info("+++++++++++++++++++++++++++Notification body: {} \n", notification.getMessage());
        try
        {
            JSONObject j = JSONObject.parseObject(notification.getMessage());
            callForOpen(j.getString(WaterMeterSwitchConstants.METHOD), j.getString(WaterMeterSwitchConstants.METERSN));
        }
        catch (Exception e)
        {
            LOG.error("message execute error :" + notification.getMessage() + "\n " + e);
        }
    }

    private void callForOpen(String method, String meterSn)
    {
        ResponseEntity<String> a = restTemplate.exchange(url, HttpMethod.POST, getHttpEntity(method, meterSn), String.class);
        JSONObject j = JSONObject.parseObject(a.getBody());
        System.out.println(j.get("data_value"));
    }

    private HttpEntity<MultiValueMap<String, String>> getHttpEntity(String method, String meterSn)
    {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        if (WaterMeterSwitchConstants.MULTIVALUEMAP_OPEN.equals(method))
        {
            multiValueMap.add(WaterMeterSwitchConstants.METHOD, WaterMeterSwitchConstants.MULTIVALUEMAP_METHOD_OPEN_VALUE);
        }
        else if (WaterMeterSwitchConstants.MULTIVALUEMAP_CLOSE.equals(method))
        {
            multiValueMap.add(WaterMeterSwitchConstants.METHOD, WaterMeterSwitchConstants.MULTIVALUEMAP_METHOD_CLOSE_VALUE);
        }
        else
        {
            throw new RuntimeException(WaterMeterSwitchConstants.ERROR_MESSAGE_METHOD);
        }
        multiValueMap.add(WaterMeterSwitchConstants.MULTIVALUEMAP_METER_SN_KEY, meterSn);
        multiValueMap.add(WaterMeterSwitchConstants.MULTIVALUEMAP_APP_SN_KEY, WaterMeterSwitchConstants.MULTIVALUEMAP_APP_SN_VALUE);
        multiValueMap.add(WaterMeterSwitchConstants.MULTIVALUEMAP_EXTEND_KEY, WaterMeterSwitchConstants.MULTIVALUEMAP_EXTEND_VALUE);
        multiValueMap.add(WaterMeterSwitchConstants.MULTIVALUEMAP_USER_TOKEN_KEY, WaterMeterSwitchConstants.MULTIVALUEMAP_USER_TOKEN_VALUE);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(multiValueMap, headers);
    }

}
