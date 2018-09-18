package com.fpt.services.lophoc.impl;

import com.fpt.entity.BoMon;
import com.fpt.entity.LopHoc;
import com.fpt.entity.MonHoc;
import com.fpt.entity.PheDuyet;
import com.fpt.repositories.bomon.BoMonDao;
import com.fpt.repositories.lophoc.LopHocDao;
import com.fpt.repositories.monhoc.MonHocDao;
import com.fpt.repositories.pheduyet.PheDuyetDao;
import com.fpt.services.lophoc.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LopHocServiceImpl implements LopHocService {
    @Autowired
    private LopHocDao lopHocDao;
    @Autowired
    private PheDuyetDao pheDuyetDao;
    @Autowired
    private MonHocDao monHocDao;

    @Override
    public LopHoc taoLopHoc(LopHoc lopHoc) {
        return lopHocDao.save(lopHoc);
    }

    @Override
    public List<LopHoc> listLopHoc()
    {
        return lopHocDao.listLopHoc();
    }

    @Override
    public List<PheDuyet> listPheDuyet() {
        return pheDuyetDao.listPheDuyet();
    }

    @Override
    public PheDuyet createPheDuyet(PheDuyet pheDuyet) {
        return pheDuyetDao.save(pheDuyet);
    }

    @Override
    public LopHoc findById(String id) {
        return lopHocDao.findOne(id);
    }

    @Override
    public List<LopHoc> searchGiaoVien(String giaovien, String bomon) {
        return lopHocDao.listSearchGiaoVien(giaovien ,  bomon);
    }

    @Override
    public List<LopHoc> searchLop(String lop, String bomon) {
        return lopHocDao.listSearchLop(lop,bomon);
    }

    @Override
    public List<MonHoc> listMonHoc() {
        return (List<MonHoc>) monHocDao.findAll();
    }


}
