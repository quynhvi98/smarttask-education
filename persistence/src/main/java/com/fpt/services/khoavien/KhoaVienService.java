package com.fpt.services.khoavien;

import com.fpt.entity.KhoaVien;

import java.util.List;
import java.util.Set;

public interface KhoaVienService {
    KhoaVien create(KhoaVien khoaVien);
    KhoaVien update(KhoaVien khoaVien);
    void delete(String id);
    KhoaVien findById(String id);
    List<KhoaVien> findAll();
    List<KhoaVien> search(String tenKhoa,String maKhoa);
}
