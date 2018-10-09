package com.fpt.services.config.impl;

import com.fpt.entity.Config;
import com.fpt.repositories.config.ConfigDao;
import com.fpt.services.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServicelmpl implements ConfigService {
    @Autowired
    private ConfigDao configDao;
    @Override
    public Config findByName(String name) {
        return configDao.findByName(name);
    }
}
