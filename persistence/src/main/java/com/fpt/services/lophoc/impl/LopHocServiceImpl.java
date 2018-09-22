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

import java.util.Arrays;
import java.util.List;

@Service
public class LopHocServiceImpl implements LopHocService {
    @Autowired
    private LopHocDao lopHocDao;


    @Override
    public LopHoc taoLopHoc(LopHoc lopHoc) {
        return lopHocDao.save(lopHoc);
    }

    @Override
    public List<LopHoc> listLopHoc() {
        return lopHocDao.listLopHoc();
    }

    @Override
    public List<LopHoc> listLopHocSinhVien(String msv) {
        return lopHocDao.listLopHocSV(msv);
    }

    @Override
    public LopHoc findById(String id) {
        return lopHocDao.findOne(id);
    }

    @Override
    public List<LopHoc> searchGiaoVien(String giaovien, String bomon) {
        return lopHocDao.listSearchGiaoVien(giaovien, bomon);
    }

    @Override
    public List<LopHoc> searchLop(String lop, String bomon) {
        return lopHocDao.listSearchLop(lop, bomon);
    }

    @Override
    public void createlopSV(LopHoc lopHoc) {
        lopHocDao.save(lopHoc);
    }

    @Override
    public Boolean checkTimeExits(String maGiaoVien, String kiHoc, String ngayHoc, String caHoc) {
        List<Object[]> lstLichHoc = lopHocDao.getSchedule(maGiaoVien, kiHoc);

        String[] arrLichHoc = null;
        String[] arrNgayHoc = null;
        String[] arrCaHoc = null;


        for (int i = 0; i < lstLichHoc.size(); i++) {
            arrLichHoc = Arrays.copyOf(lstLichHoc.get(i), lstLichHoc.get(i).length, String[].class);
            arrNgayHoc = arrLichHoc[0].split(",");
            arrCaHoc = arrLichHoc[1].split(",");

            for (int j = 0; j < arrNgayHoc.length; j++) {
                if (ngayHoc.equals(arrNgayHoc[j]) && caHoc.equals(arrCaHoc[j])) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public List<LopHoc> findAll() {
        return (List<LopHoc>) lopHocDao.findAll();
    }

    @Override
    public LopHoc getLopHocSV(String magv, String masv) {
        return lopHocDao.getLopHocSv(magv, masv);
    }

    @Override
    public LopHoc getLopHocSvBm(String masv, String mabomon) {
        return lopHocDao.getLopHocSvBm(masv, mabomon);
    }


}
