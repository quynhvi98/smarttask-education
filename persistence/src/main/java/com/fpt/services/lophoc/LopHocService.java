package com.fpt.services.lophoc;

import com.fpt.entity.BoMon;
import com.fpt.entity.LopHoc;
import com.fpt.entity.MonHoc;
import com.fpt.entity.PheDuyet;

import java.util.List;

public interface LopHocService {
    LopHoc taoLopHoc(LopHoc lopHoc);
    List<LopHoc> listLopHoc();
    List<PheDuyet> listPheDuyet();
    PheDuyet createPheDuyet(PheDuyet pheDuyet);
    LopHoc findById(String id);
    List<LopHoc> searchGiaoVien(String giaovien,String bomon);
    List<LopHoc> searchLop(String lop,String bomon);
    List<MonHoc> listMonHoc();

    List<LopHoc> findAll();
}
