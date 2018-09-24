package com.fpt.services.lophoc;

import com.fpt.entity.BoMon;
import com.fpt.entity.LopHoc;
import com.fpt.entity.MonHoc;
import com.fpt.entity.PheDuyet;

import java.util.List;

public interface LopHocService {
    LopHoc taoLopHoc(LopHoc lopHoc);
    List<LopHoc> listLopHoc();
    List<LopHoc> listLopHocSinhVien(String msv);
    LopHoc findById(String id);
    List<LopHoc> searchGiaoVien(String giaovien,String bomon);
    List<LopHoc> searchLop(String lop,String bomon);
    List<LopHoc> findAll();
    LopHoc getLopHocSV(String malop,String masv);
    LopHoc getLopHocSvBm(String masv,String mabomon);
    void createlopSV(LopHoc lopHoc);
    List<LopHoc> getListLopHoc(String maGiaoVien);

    Boolean checkTimeExits(String maGiaoVien, String kiHoc, String ngayHoc, String caHoc);

    Long count();
}
