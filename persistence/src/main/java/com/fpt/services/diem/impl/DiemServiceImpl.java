package com.fpt.services.diem.impl;

import com.fpt.entity.Diem;
import com.fpt.repositories.diem.DiemDao;
import com.fpt.services.diem.DiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiemServiceImpl implements DiemService {
    @Autowired
    DiemDao diemDao;


    @Override
    public void careate(List<Diem> diems) {
        for (Diem diem: diems) {
            diemDao.save(diem);
        }
    }

    @Override
    public Diem save(Diem diem) {
        return diemDao.save(diem);
    }

    @Override
    public Diem findById(String id) {
        return diemDao.findOne(id);
    }

    @Override
    public List<Diem> listDiemKi(String msv, int ki) {
        return diemDao.listDiemKi(msv,ki);
    }
}
