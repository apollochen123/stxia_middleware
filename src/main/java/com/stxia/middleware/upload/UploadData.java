
/**
 * Project Name:com.stxia.middleware 
 * File Name:UploadData.java <br/><br/>  
 * Description: UploadData
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
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.kaaproject.kaa.client.DesktopKaaPlatformContext;
import org.kaaproject.kaa.client.Kaa;
import org.kaaproject.kaa.client.KaaClient;
import org.kaaproject.kaa.client.SimpleKaaClientStateListener;
import org.kaaproject.kaa.client.logging.strategies.RecordCountLogUploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hyy.cn.hyy_mdjl;
import com.stxia.middleware.dto.HwaDataDto;
import com.stxia.middleware.dto.HwaDto;
import com.stxia.middleware.upload.constants.UploadDataConstants;

/**
 * ClassName: UploadData <br/>
 * <br/>
 * Description: UploadData
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

    @Value("${stxia.url.host}")
    private String url;

    private static Logger LOG = Logger.getLogger(UploadData.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // 每天早上6点执行
    //@Scheduled(cron = "0 0 6 * * ?")
    @Scheduled(initialDelay = 10000, fixedDelay = 100000)
    public void timerInit()
    {
        sendData();
    }

    //call rest API to get All data
    private HwaDto getAllHwaData()
    {
        LOG.info("-----------------startGetAllHwaData------------------");
        LOG.info("-----------------date:" + dateFormat.format(System.currentTimeMillis()) + "--------------");

        UriComponentsBuilder urlVariables = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam(UploadDataConstants.METHOD_KEY, UploadDataConstants.METHOD_VALUE)
            .queryParam(UploadDataConstants.APP_SN_KEY, UploadDataConstants.APP_SN_VALUE)
            .queryParam(UploadDataConstants.EXTEND_KEY, UploadDataConstants.EXTEND_VALUE)
            .queryParam(UploadDataConstants.USER_TOKEN_KEY, UploadDataConstants.USER_TOKEN_VALUE)
            .queryParam(UploadDataConstants.DAY_KEY, dateFormat.format(System.currentTimeMillis()));

        HwaDto result = restTemplate.getForObject(urlVariables.build().encode().toUri(), HwaDto.class);
        LOG.info("-----------------RequestSuccess------------------");
        return result;
    }

    private void sendData()
    {
        LOG.info("-----------------startKaaClient------------------");
        KaaClient kaaClient = Kaa.newClient(new DesktopKaaPlatformContext(), new SimpleKaaClientStateListener(), true);
        //配置上传策略
        RecordCountLogUploadStrategy strategy = new RecordCountLogUploadStrategy(1);
        strategy.setMaxParallelUploads(1);
        kaaClient.setLogUploadStrategy(strategy);
        // 启动
        kaaClient.start();

        List<HwaDataDto> uploadDatas = getAllHwaData().getData_value();

        if (null == uploadDatas)
        {
            kaaClient.stop();
            throw new RuntimeException(UploadDataConstants.GETALL_ERROR_MESSAGE);
        }

        for (HwaDataDto hw : uploadDatas)
        {
            try
            {
                kaaClient.addLogRecord(generateHY(hw)).get();
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }
        LOG.info("-----------------upload finished------------------");
        kaaClient.stop();
    }

    private hyy_mdjl generateHY(HwaDataDto hw)
    {
        hyy_mdjl hy = new hyy_mdjl();
        hy.setCurrentHeat(hw.getCurrentHeat());
        hy.setFlowRate(hw.getFlowRate());
        hy.setInputTemperature(hw.getInputTemperature());
        hy.setOutPutTemperature(hw.getOutPutTemperature());
        hy.setRealTime(hw.getRealTime());
        hy.setStatus(hw.getStatus());
        hy.setThermalPower(hw.getThermalPower());
        hy.setTotalFlow(hw.getTotalFlow());
        hy.setTotalWorkTime(hw.getTotalWorkTime());
        hy.setUUID(hw.getEpid());
        hy.setValveClosingTime(hw.getValveOpeningTime());
        hy.setValveOpeningStatus(hw.getValveOpeningStatus());
        hy.setValveOpeningTime(hw.getValveOpeningTime());
        hy.setValveRealTime(hw.getValveRealTime());
        return hy;
    }

}
