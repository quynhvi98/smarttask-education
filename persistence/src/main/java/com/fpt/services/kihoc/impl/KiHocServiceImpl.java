package com.fpt.services.kihoc.impl;

import com.fpt.entity.KiHoc;
import com.fpt.repositories.kihoc.KiHocDao;
import com.fpt.services.kihoc.KiHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KiHocServiceImpl implements KiHocService {
    @Autowired
    private KiHocDao kiHocDao;

    @Override
    public KiHoc findById(Integer id) {
        return kiHocDao.findOne(id);
    }
}
