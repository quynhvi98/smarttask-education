package com.fpt.services.giangvien.impl;

import com.fpt.entity.GiaoVien;
import com.fpt.repositories.giangvien.GiangVienDao;
import com.fpt.services.giangvien.GiangVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiangVienServiceImpl implements GiangVienService {
    @Autowired
    GiangVienDao giangVienDao;

    @Override
    public GiaoVien themGiaoVien(GiaoVien giaoVien) {
        return giangVienDao.save(giaoVien);
    }

    @Override
    public List<GiaoVien> findAll() {
        return (List<GiaoVien>) giangVienDao.findAll();
    }

    @Override
    public GiaoVien findById(String giangVienId) {
        return giangVienDao.findOne(giangVienId);
    }

    @Override
    public List<GiaoVien> getLstGiangVienByMaNganh(String maNganh) {
        return giangVienDao.getLstGiangVienByMaNganh(maNganh);
    }
}
