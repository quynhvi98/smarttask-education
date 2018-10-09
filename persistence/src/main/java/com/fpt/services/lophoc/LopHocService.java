package com.fpt.services.lophoc;

import com.fpt.entity.LopHoc;

import java.util.List;

public interface LopHocService {
    LopHoc taoLopHoc(LopHoc lopHoc);
    void capNhat(LopHoc lopHoc);
    List<LopHoc> listLopHoc();
    List<LopHoc> listLopHocSinhVien(String msv);
    LopHoc findById(String id);
    List<LopHoc> searchGiaoVien(String giaovien,String bomon);
    List<LopHoc> searchLop(String lop,String bomon);
    List<LopHoc> findAll();
    LopHoc getLopHocSV(String fullName,String tenMonHoc);
    int getTongTinSvKi(String masv,int ki);
    LopHoc getLopHocSvBm(String masv,String mabomon);
    void createlopSV(LopHoc lopHoc);
    List<LopHoc> getListLopHoc(String maGiaoVien);
    String tinhNgayHetHan(int soNgay);
    List<LopHoc> listLopToiHan(String day1,String day2);
    List<LopHoc> listDongLop(String day);
    Boolean checkTimeExits(String maGiaoVien, String kiHoc, String ngayHoc, String caHoc);
    List<LopHoc> search(String maLop,String phongHoc);
    String ngayHienTai();
    Long count();
}
