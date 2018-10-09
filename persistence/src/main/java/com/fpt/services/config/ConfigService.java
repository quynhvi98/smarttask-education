package com.fpt.services.config;

import com.fpt.entity.Config;

public interface ConfigService {
    Config findByName(String name);
}
