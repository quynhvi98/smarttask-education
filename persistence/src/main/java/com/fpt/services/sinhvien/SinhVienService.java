package com.fpt.services.sinhvien;

import com.fpt.entity.SinhVien;

import java.util.List;

public interface SinhVienService {
    SinhVien themSinhVien(SinhVien sinhVien);
    List<SinhVien> listSV();
    SinhVien getSinhVienId(String id);
    List<SinhVien> getListSinhVienbyLopHocId(String maLop);
    void save(List<SinhVien> lstSV);
}
