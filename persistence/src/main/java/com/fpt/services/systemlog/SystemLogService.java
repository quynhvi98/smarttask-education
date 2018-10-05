package com.fpt.services.systemlog;

import com.fpt.entity.SystemLog;

import java.util.List;

public interface SystemLogService {
    void save(SystemLog systemLog);
    List<SystemLog> findAll();
}
