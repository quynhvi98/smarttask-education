package com.fpt.services.lophoc.impl;

import com.fpt.entity.LopHoc;
import com.fpt.repositories.lophoc.LopHocDao;
import com.fpt.services.lophoc.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LopHocServiceImpl implements LopHocService {
    @Autowired
    private LopHocDao lopHocDao;


    @Override
    public LopHoc taoLopHoc(LopHoc lopHoc) {
        return lopHocDao.save(lopHoc);
    }

    @Override
    public void capNhat(LopHoc lopHoc) {
        lopHocDao.save(lopHoc);
    }

    @Override
    public List<LopHoc> listLopHoc() {
        return lopHocDao.listLopHoc();
    }

    @Override
    public List<LopHoc> listLopHocSinhVien(String msv) {

        return lopHocDao.listLopHocSV(msv,ngayHienTai());
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
    public List<LopHoc> getListLopHoc(String maGiaoVien) {
        return lopHocDao.getListLopHoc(maGiaoVien);
    }

    @Override
    public String tinhNgayHetHan(int soNgay) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String time=dateFormat.format(date);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE,+soNgay);
        String wantedDate = sdf.format(cal.getTime());
        return wantedDate;
    }

    @Override
    public List<LopHoc> listLopToiHan(String day1, String day2) {
        return lopHocDao.listLopToiHan(day1,day2);
    }

    @Override
    public List<LopHoc> listDongLop(String day) {
        return lopHocDao.listDongLop(day);
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
    public List<LopHoc> search(String fullName, String tenMonHoc) {
        return lopHocDao.search(fullName,tenMonHoc);
    }

    @Override
    public String ngayHienTai() {
        String newdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return newdate;
    }

    @Override
    public Long count() {
        return lopHocDao.count();
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
    public int getTongTinSvKi(String masv, int ki) {
        int tongTin=0;
        for (LopHoc lopHoc: lopHocDao.getLopHocSvKi(masv,ki)){
          tongTin=tongTin+ lopHoc.getMonHoc().getTinChi();
        }
        return tongTin;
    }

    @Override
    public LopHoc getLopHocSvBm(String masv, String mabomon) {
        return lopHocDao.getLopHocSvBm(masv, mabomon);
    }


}
