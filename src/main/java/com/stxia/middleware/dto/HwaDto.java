
/**
 * Project Name:com.stxia.middleware 
 * File Name:HwaDto.java <br/><br/>  
 * Description: HwaDto
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Nov 23, 2017 1:03:52 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.dto;

import java.util.List;

/**
 * ClassName: HwaDto <br/>
 * <br/>
 * Description: HwaDto
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
public class HwaDto
{
    private int return_code;
    private List<HwaDataDto> data_value;
    private List<Object> data_array;
    private List<Object> ext_array;

    
     /**
      * create an instance: HwaDto. 
      * Title: HwaDto <br/><br/> 
      * Description: HwaDto
      * @param return_code
      * @param data_value
      * @param data_array
      * @param ext_array
      */
    public HwaDto(int return_code, List<HwaDataDto> data_value, List<Object> data_array, List<Object> ext_array)
    {
        super();
        this.return_code = return_code;
        this.data_value = data_value;
        this.data_array = data_array;
        this.ext_array = ext_array;
    }

    
     /**
      * create an instance: HwaDto. 
      * Title: HwaDto <br/><br/> 
      * Description: 
      */
    public HwaDto()
    {
         super();  
    }

    public int getReturn_code()
    {

        return return_code;
    }

    public void setReturn_code(int return_code)
    {
        this.return_code = return_code;
    }

    public List<HwaDataDto> getData_value()
    {

        return data_value;
    }

    public void setData_value(List<HwaDataDto> data_value)
    {
        this.data_value = data_value;
    }

    public List<Object> getData_array()
    {
    
        return data_array;
    }

    public void setData_array(List<Object> data_array)
    {
        this.data_array = data_array;
    }

    public List<Object> getExt_array()
    {
    
        return ext_array;
    }

    public void setExt_array(List<Object> ext_array)
    {
        this.ext_array = ext_array;
    }
}
