package com.fpt.services.bomon.impl;

import com.fpt.entity.BoMon;
import com.fpt.repositories.bomon.BoMonDao;
import com.fpt.services.bomon.BoMonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoMonServiceImpl implements BoMonService {
    @Autowired
    private BoMonDao boMonDao;

    @Override
    public BoMon taoBoMon(BoMon boMon) {
        return boMonDao.save(boMon);
    }
}
