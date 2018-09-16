package com.fpt.services.khoavien.impl;

import com.fpt.entity.KhoaVien;
import com.fpt.repositories.khoavien.KhoaVienDao;
import com.fpt.services.khoavien.KhoaVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class KhoaVienServiceImpl implements KhoaVienService {
    @Autowired
    private KhoaVienDao khoaVienDao;

    @Override
    public KhoaVien create(KhoaVien khoaVien) {
        return khoaVienDao.save(khoaVien);
    }

    @Override
    public KhoaVien update(KhoaVien khoaVien) {
        return khoaVienDao.save(khoaVien);
    }

    @Override
    public void delete(String id) {
        khoaVienDao.delete(id);
    }

    @Override
    public KhoaVien findById(String id) {
        return khoaVienDao.findOne(id);
    }

    @Override
    public List<KhoaVien> findAll() {
        return (List<KhoaVien>) khoaVienDao.findAll();
    }
}
