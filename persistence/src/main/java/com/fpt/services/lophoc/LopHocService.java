package com.fpt.services.lophoc;


import com.fpt.entity.LopHoc;



import java.util.List;

public interface LopHocService {
    LopHoc taoLopHoc(LopHoc lopHoc);
    List<LopHoc> listLopHoc();
    List<LopHoc> listLopHocSinhVien(String msv);
    LopHoc findById(String id);
    List<LopHoc> searchGiaoVien(String giaovien,String bomon);
    List<LopHoc> searchLop(String lop,String bomon);
    void createlopSV(LopHoc lopHoc);
}
