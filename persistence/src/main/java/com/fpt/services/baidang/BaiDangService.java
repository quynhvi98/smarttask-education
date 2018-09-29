package com.fpt.services.baidang;

import com.fpt.entity.BaiDang;

import java.util.List;

public interface BaiDangService {
    void save(BaiDang baiDang);

    List<BaiDang> findAll();

    List<BaiDang> findByMaLop(String maLop);

    BaiDang findById(Integer postId);

    void delete(Integer postId);
}
