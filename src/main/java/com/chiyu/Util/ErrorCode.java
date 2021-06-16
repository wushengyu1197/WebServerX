package com.chiyu.Util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {

    PARAM_ERROR("E0001"),//参数错误
    DB_ERROR("E0002"),//数据库错误
    PROGRAM_ERROR("E0003"),//程序异常
    REQUEST_ERROR("E0004"),//请求错误
    SENTINEL_ERROR("E0005"),//sentinel错误
    LOGINSTATUS_ERROR("E0006"),//登录态错误
    UPLOADFILE_SIZElIMIT_ERROR("E0007");//上传文件大小超限制

    /**
     * PARAM_ERROR 参数错误
     * DB_ERROR  数据库错误
     * PROGRAM_ERROR  程序处理过程中报错
     * REQUEST_ERROR  请求时报错，还未进入程序处理
     */

    @JsonValue
    private String value;

    ErrorCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
