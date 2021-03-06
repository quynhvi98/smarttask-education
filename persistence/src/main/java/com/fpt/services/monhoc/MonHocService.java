package com.fpt.services.monhoc;

import com.fpt.entity.MonHoc;

import java.util.List;

public interface MonHocService {
    MonHoc taoMonHoc(MonHoc monHoc);

    List<MonHoc> findAll();

    MonHoc findById(String monHocId);

    void update(MonHoc persist);
    List<MonHoc> listMonHoc();
    List<MonHoc> listMonHocKy(int hocki,String mv);

    List<MonHoc> getLstMonHocByHocKiAndBoMon(String boMon, Integer kiHoc);

    Long count();
List<MonHoc> searchMonHoc(String tenMonHoc,String maMonHoc);

    int tongMonHocKiVaBoMon(String boMon, Integer kiHoc);
}
