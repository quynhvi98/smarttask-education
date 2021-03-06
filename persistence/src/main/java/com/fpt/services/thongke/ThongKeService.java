package com.fpt.services.thongke;

import com.fpt.entity.ThongKe;

import java.util.List;

public interface ThongKeService {
    List<ThongKe> getThongKe(String kiHoc);
    List<Object> getLstLopByMaGiaoVien(String maGv);

    List<Object> getLstLopByMaSinhVien(String id);
}
