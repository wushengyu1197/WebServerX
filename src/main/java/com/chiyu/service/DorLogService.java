package com.chiyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chiyu.domin.DorLog;

import java.util.List;


public interface DorLogService extends IService<DorLog> {

    List<DorLog> listDorLog();
}