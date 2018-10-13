package com.fpt.services.bomon.impl;

import com.fpt.entity.BoMon;
import com.fpt.repositories.bomon.BoMonDao;
import com.fpt.services.bomon.BoMonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoMonServiceImpl implements BoMonService {
    @Autowired
    private BoMonDao boMonDao;

    @Override
    public BoMon taoBoMon(BoMon boMon) {
        return boMonDao.save(boMon);
    }

    @Override
    public List<BoMon> findAll() {
        return (List<BoMon>) boMonDao.findAll();
    }

    @Override
    public BoMon findById(String boMon) {
        return boMonDao.findOne(boMon);
    }

    @Override
    public List<BoMon> searchBoMon(String tenBoMon, String maBoMon) {
        return boMonDao.search(tenBoMon,maBoMon);
    }

    @Override
    public void update(BoMon persist) {
        boMonDao.save(persist);
    }

    @Override
    public List<BoMon> getLstBoMonByMaVien(String khoaVien) {
        return boMonDao.getLstBoMonByMaVien(khoaVien);
    }
}
