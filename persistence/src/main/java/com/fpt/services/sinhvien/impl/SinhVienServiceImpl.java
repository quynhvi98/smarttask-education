package com.fpt.services.sinhvien.impl;

import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.repositories.sinhvien.SinhVienDao;
import com.fpt.repositories.user.UserDao;
import com.fpt.services.sinhvien.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SinhVienServiceImpl implements SinhVienService {
    @Autowired
    private SinhVienDao sinhVienDao;
    @Autowired
    private UserDao userDao;

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

    @Override
    @Transactional
    public void save(List<SinhVien> lstSV) {
        for (SinhVien sv: lstSV) {
            User user = userDao.save(sv.getUser());
            sv.setUser(user);
            sinhVienDao.save(sv);
        }
    }
}
