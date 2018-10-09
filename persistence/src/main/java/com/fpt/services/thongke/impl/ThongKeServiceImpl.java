package com.fpt.services.thongke.impl;

import com.fpt.entity.ThongKe;
import com.fpt.repositories.thongke.ThongKeDao;
import com.fpt.services.thongke.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThongKeServiceImpl implements ThongKeService {

    @Autowired
    ThongKeDao thongKeDao;

    @Override
    public List<ThongKe> getThongKe(String kiHoc) {
        return thongKeDao.getThongKe(kiHoc);
    }

    @Override
    public List<Object> getLstLopByMaGiaoVien(String maGv) {
        return thongKeDao.getLstLopByMaGiaoVien(maGv);
    }

    @Override
    public List<Object> getLstLopByMaSinhVien(String id) {
        return thongKeDao.getLstLopByMaSinhVien(id);
    }
}
