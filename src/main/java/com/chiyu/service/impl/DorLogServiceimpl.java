package com.chiyu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chiyu.domin.DorLog;
import com.chiyu.mapper.DorLogMapper;
import com.chiyu.service.DorLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DorLogServiceimpl extends ServiceImpl<DorLogMapper, DorLog> implements DorLogService {
   @Resource
   DorLogMapper dorLogMapper;

    @Override
    public List<DorLog> listDorLog() {
        return dorLogMapper.listDorLog();
    }

}
