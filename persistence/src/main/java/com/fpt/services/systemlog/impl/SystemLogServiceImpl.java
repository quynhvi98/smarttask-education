package com.fpt.services.systemlog.impl;

import com.fpt.entity.SystemLog;
import com.fpt.repositories.systemlog.SystemLogDao;
import com.fpt.services.systemlog.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemLogServiceImpl  implements SystemLogService {

    @Autowired
    SystemLogDao systemLogDao;

    @Override
    public void save(SystemLog systemLog) {
        systemLogDao.save(systemLog);
    }

    @Override
    public List<SystemLog> findAll() {
        return systemLogDao.findAllSort();
    }
}
