package com.fpt.services.phonghoc;

import com.fpt.entity.MonHoc;
import com.fpt.entity.PhongHoc;

import java.util.List;

public interface PhongHocService {
    List<PhongHoc> getAll();

    List<PhongHoc> getAvailableClass(String[] ngayHoc, String[] caHoc);

    PhongHoc findById(String phongHoc);
}
