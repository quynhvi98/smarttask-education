package com.fpt.services.monhoc;

import com.fpt.entity.MonHoc;

import java.util.List;

public interface MonHocService {
    MonHoc taoMonHoc(MonHoc monHoc);

    List<MonHoc> findAll();

    MonHoc findById(String monHocId);

    void update(MonHoc persist);
}
