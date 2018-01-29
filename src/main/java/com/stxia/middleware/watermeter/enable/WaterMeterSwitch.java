
/**
 * Project Name:com.stxia.middleware 
 * File Name:WaterMeterSwitch.java <br/><br/>  
 * Description: WaterMeterSwitch
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Nov 21, 2017 3:22:59 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.watermeter.enable;

import java.util.ArrayList;
import java.util.List;

import org.kaaproject.kaa.client.DesktopKaaPlatformContext;
import org.kaaproject.kaa.client.Kaa;
import org.kaaproject.kaa.client.KaaClient;
import org.kaaproject.kaa.client.SimpleKaaClientStateListener;
import org.kaaproject.kaa.client.notification.NotificationListener;
import org.kaaproject.kaa.client.notification.NotificationTopicListListener;
import org.kaaproject.kaa.common.endpoint.gen.SubscriptionType;
import org.kaaproject.kaa.common.endpoint.gen.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.stxia.middleware.watermeter.enable.constants.WaterMeterSwitchConstants;

/**
 * ClassName: WaterMeterSwitch <br/>
 * <br/>
 * Description: WaterMeterSwitch
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
public class WaterMeterSwitch
{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${stxia.url.open}")
    private String url;

    private static final Logger LOG = LoggerFactory.getLogger(WaterMeterSwitch.class);

    private static KaaClient kaaClient;
    //所有可以订阅的主题
    private static List<Topic> topics;
    //已经订阅的主题
    private static List<Topic> subscribedTopics = new ArrayList<Topic>();

    public void swi()
    {
        kaaClient = Kaa.newClient(new DesktopKaaPlatformContext(), new SimpleKaaClientStateListener(), true);

        /*
         * A listener that listens to the notification topic list updates.
         */
        NotificationTopicListListener topicListListener = new BasicNotificationTopicListListener();
        kaaClient.addTopicListListener(topicListListener);

        /*
         * Add a notification listener that listens to all notifications.
         */
        kaaClient.addNotificationListener(new NotificationListener()
        {

            @Override
            public void onNotification(long id, NotificationPoint notification)
            {
                LOG.info("Notification from the topic with id [{}] and name [{}] received.", id, getTopic(id).getName());
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
                MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
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
        });
        /*
         * Start the Kaa client and connect it to the Kaa server.
         */
        kaaClient.start();
    }

    /**
     * A listener that tracks the notification topic list updates and subscribes the Kaa client to every new topic
     * available.
     */
    private static class BasicNotificationTopicListListener implements NotificationTopicListListener
    {
        @Override
        public void onListUpdated(List<Topic> list)
        {
            LOG.info("Topic list was updated:");
            topics.clear();
            topics.addAll(list);

            showTopics();
        }
    }

    private static void showTopics()
    {
        if (topics == null || topics.isEmpty())
        {
            LOG.info("Topic list is empty");
            return;
        }

        LOG.info("Available topics:");
        for (Topic topic : topics)
        {
            LOG.info("Topic id: {}, name: {}, type: {}", topic.getId(), topic.getName(), topic.getSubscriptionType());
        }

        LOG.info("Subscribed on topics:");
        for (Topic t : getOneTypeTopics(SubscriptionType.MANDATORY_SUBSCRIPTION))
        {
            LOG.info("Topic id: {}, name: {}, type: {}", t.getId(), t.getName(), t.getSubscriptionType().name());
        }
        /*
         * Optional topics
         */
        if (!subscribedTopics.isEmpty())
        {
            for (Topic t : subscribedTopics)
            {
                LOG.info("Topic id: {}, name: {}, type: {}", t.getId(), t.getName(), t.getSubscriptionType().name());
            }
        }
    }

    private static List<Topic> getOneTypeTopics(SubscriptionType type)
    {
        List<Topic> res = new ArrayList<>();
        for (Topic t : WaterMeterSwitch.topics)
        {
            if (t.getSubscriptionType() == type)
            {
                res.add(t);
            }
        }
        return res;
    }

    private static Topic getTopic(long id)
    {
        for (Topic t : topics)
            if (t.getId() == id)
                return t;
        return null;
    }
}
