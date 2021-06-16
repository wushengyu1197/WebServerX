package com.chiyu.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseResult {
    private int code;
    private String res_message;
    private boolean success;
    //private Object res_data;
   // private String timestamp;
    //返回数据格式，定义code、message、res_data
    public ResponseResult result_failure(int code,String res_message,Object res_data) {
        this.code = code;
        this.res_message =res_message;
        this.success = false;
        //this.timestamp = com.rfid.instantDeploy.common.TurnDate.getStringDateTime();
        //this.res_data = res_data;
        return this;
    }
    //返回数据格式，定义code、message、res_data
    public ResponseResult result_data(int code,String res_message,Object res_data) {
        this.code = code;
        this.res_message =res_message;
        this.success = true;
       // this.timestamp = TurnDate.getStringDateTime();
        //this.res_data = res_data;
        return this;
    }
}
