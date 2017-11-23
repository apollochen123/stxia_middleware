
/**
 * Project Name:com.stxia.middleware 
 * File Name:ScheduleConfig.java <br/><br/>  
 * Description: ScheduleConfig
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Nov 21, 2017 3:57:56 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.configuration;

import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * ClassName: ScheduleConfig <br/>
 * <br/>
 * Description: ScheduleConfig
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
//@Configuration
public class ScheduleConfig implements SchedulingConfigurer
{
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
    {
        //申请5个线程池给Schedule任务
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
    }
}
