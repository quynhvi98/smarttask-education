package com.fpt.services.giangvien;

import com.fpt.entity.BoMon;
import com.fpt.entity.GiaoVien;

import java.util.List;

public interface GiangVienService {
    GiaoVien themGiaoVien(GiaoVien giaoVien);
    List<GiaoVien> findAll();

    GiaoVien findById(String giangVienId);

    List<GiaoVien> getLstGiangVienByMaNganh(String maNganh);
}
