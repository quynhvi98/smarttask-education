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

    @Override
    public List<MonHoc> listMonHocKy(int hocki,String mv) {
        return monHocDao.listMonHocKy(hocki,mv);
    }

    @Override
    public List<MonHoc> getLstMonHocByHocKiAndBoMon(String boMon, Integer kiHoc) {
        return monHocDao.getLstMonHocByHocKiAndBoMon(boMon, kiHoc);
    }

    @Override
    public Long count() {
        return monHocDao.count();
    }

    @Override
    public List<MonHoc> searchMonHoc(String tenMonHoc, String maMonHoc) {
        return monHocDao.search(tenMonHoc,maMonHoc);
    }

    @Override
    public int tongMonHocKiVaBoMon(String boMon, Integer kiHoc) {
        return monHocDao.getLstMonHocByHocKiAndBoMon(boMon,kiHoc).size();
    }


    @Override
    public List<MonHoc> findAll() {
        return (List<MonHoc>) monHocDao.findAll();
    }

    @Override
    public MonHoc findById(String monHocId) {
        return monHocDao.findOne(monHocId);
    }

    @Override
    public void update(MonHoc persist) {
        monHocDao.save(persist);
    }
}
