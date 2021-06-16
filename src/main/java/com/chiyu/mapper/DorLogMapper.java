package com.chiyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chiyu.domin.DorLog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DorLogMapper extends BaseMapper<DorLog> {
    //根据三个字段去重
    @Select("SELECT DISTINCT DATETIME,DoorNo,TerminalID,InOutIndication FROM dorloga ")
    List<DorLog> listDorLog();
}