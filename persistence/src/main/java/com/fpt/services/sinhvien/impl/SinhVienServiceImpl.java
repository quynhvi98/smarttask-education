package com.fpt.services.sinhvien.impl;

import com.fpt.entity.SinhVien;
import com.fpt.repositories.sinhvien.SinhVienDao;
import com.fpt.services.sinhvien.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienServiceImpl implements SinhVienService {
    @Autowired
    private SinhVienDao sinhVienDao;

    @Override
    public SinhVien themSinhVien(SinhVien sinhVien) {
        return sinhVienDao.save(sinhVien);
    }

    @Override
    public List<SinhVien> listSV() {
        return sinhVienDao.listSV();
    }

    @Override
    public SinhVien getSinhVienId(String id) {
        return sinhVienDao.findOne(id);
    }

    @Override
    public List<SinhVien> getListSinhVienbyLopHocId(String maLop) {
        return sinhVienDao.getListSinhVienbyLopHocId(maLop);
    }
}
