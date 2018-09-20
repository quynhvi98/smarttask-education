package com.fpt.services.monhoc.impl;

import com.fpt.entity.MonHoc;
import com.fpt.repositories.monhoc.MonHocDao;
import com.fpt.services.monhoc.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonHocServiceImpl implements MonHocService {
    @Autowired
    private MonHocDao monHocDao;

    @Override
    public MonHoc taoMonHoc(MonHoc monHoc) {
        return monHocDao.save(monHoc);
    }

    @Override
    public List<MonHoc> listMonHoc() {
        return (List<MonHoc>) monHocDao.findAll();
    }
}
