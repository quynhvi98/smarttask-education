package com.fpt.services.khoavien.impl;

import com.fpt.entity.KhoaVien;
import com.fpt.repositories.khoavien.KhoaVienDao;
import com.fpt.services.khoavien.KhoaVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KhoaVienServiceImpl implements KhoaVienService {
    @Autowired
    private KhoaVienDao khoaVienDao;

    @Override
    public KhoaVien taoKhoaVien(KhoaVien khoaVien) {
        return khoaVienDao.save(khoaVien);
    }
}
