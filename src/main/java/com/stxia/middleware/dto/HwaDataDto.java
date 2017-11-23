
/**
 * Project Name:com.stxia.middleware 
 * File Name:HwaDataDto.java <br/><br/>  
 * Description: HwaDataDto
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Nov 23, 2017 11:12:31 AM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.dto;

/**
 * ClassName: HwaDataDto <br/>
 * <br/>
 * Description: HwaDataDto
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
public class HwaDataDto
{
    private String epid;
    private String currentHeat;
    private String thermalPower;
    private String flowRate;
    private String totalFlow;
    private String inputTemperature;
    private String outPutTemperature;
    private String realTime;
    private String totalWorkTime;
    private String status;
    private String valveOpeningTime;
    private String valveClosingTime;
    private String valveOpeningStatus;
    private String valveRealTime;

    public String getCurrentHeat()
    {

        return currentHeat;
    }

    public void setCurrentHeat(String currentHeat)
    {
        this.currentHeat = currentHeat;
    }

    public String getThermalPower()
    {

        return thermalPower;
    }

    public void setThermalPower(String thermalPower)
    {
        this.thermalPower = thermalPower;
    }

    public String getFlowRate()
    {

        return flowRate;
    }

    public void setFlowRate(String flowRate)
    {
        this.flowRate = flowRate;
    }

    public String getTotalFlow()
    {

        return totalFlow;
    }

    public void setTotalFlow(String totalFlow)
    {
        this.totalFlow = totalFlow;
    }

    public String getInputTemperature()
    {

        return inputTemperature;
    }

    public void setInputTemperature(String inputTemperature)
    {
        this.inputTemperature = inputTemperature;
    }

    public String getOutPutTemperature()
    {

        return outPutTemperature;
    }

    public void setOutPutTemperature(String outPutTemperature)
    {
        this.outPutTemperature = outPutTemperature;
    }

    public String getRealTime()
    {

        return realTime;
    }

    public void setRealTime(String realTime)
    {
        this.realTime = realTime;
    }

    public String getTotalWorkTime()
    {

        return totalWorkTime;
    }

    public void setTotalWorkTime(String totalWorkTime)
    {
        this.totalWorkTime = totalWorkTime;
    }

    public String getStatus()
    {

        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getValveOpeningTime()
    {

        return valveOpeningTime;
    }

    public void setValveOpeningTime(String valveOpeningTime)
    {
        this.valveOpeningTime = valveOpeningTime;
    }

    public String getValveClosingTime()
    {

        return valveClosingTime;
    }

    public void setValveClosingTime(String valveClosingTime)
    {
        this.valveClosingTime = valveClosingTime;
    }

    public String getValveOpeningStatus()
    {

        return valveOpeningStatus;
    }

    public void setValveOpeningStatus(String valveOpeningStatus)
    {
        this.valveOpeningStatus = valveOpeningStatus;
    }

    public String getValveRealTime()
    {

        return valveRealTime;
    }

    public void setValveRealTime(String valveRealTime)
    {
        this.valveRealTime = valveRealTime;
    }

    /**
     * create an instance: HwaDataDto. Title: HwaDataDto <br/>
     * <br/>
     * Description:
     * 
     * @param EPID
     * @param currentHeat
     * @param thermalPower
     * @param flowRate
     * @param totalFlow
     * @param inputTemperature
     * @param outPutTemperature
     * @param realTime
     * @param totalWorkTime
     * @param status
     * @param valveOpeningTime
     * @param valveClosingTime
     * @param valveOpeningStatus
     * @param valveRealTime
     */
    public HwaDataDto(String epid, String currentHeat, String thermalPower, String flowRate, String totalFlow, String inputTemperature,
        String outPutTemperature, String realTime, String totalWorkTime, String status, String valveOpeningTime, String valveClosingTime,
        String valveOpeningStatus, String valveRealTime)
    {
        super();
        this.epid = epid;
        this.currentHeat = currentHeat;
        this.thermalPower = thermalPower;
        this.flowRate = flowRate;
        this.totalFlow = totalFlow;
        this.inputTemperature = inputTemperature;
        this.outPutTemperature = outPutTemperature;
        this.realTime = realTime;
        this.totalWorkTime = totalWorkTime;
        this.status = status;
        this.valveOpeningTime = valveOpeningTime;
        this.valveClosingTime = valveClosingTime;
        this.valveOpeningStatus = valveOpeningStatus;
        this.valveRealTime = valveRealTime;
    }

    /**
     * create an instance: HwaDataDto. Title: HwaDataDto <br/>
     * <br/>
     * Description:
     */
    public HwaDataDto()
    {
        super();
    }

    public String getEpid()
    {
    
        return epid;
    }

    public void setEpid(String epid)
    {
        this.epid = epid;
    }
}
