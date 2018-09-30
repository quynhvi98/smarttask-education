package com.fpt.services.tailieu;

import com.fpt.entity.TaiLieu;

import java.util.List;

public interface TaiLieuService {
    void save(TaiLieu taiLieu);
    List<TaiLieu> findAllByMaLop(String maLop);

    TaiLieu findById(Integer docId);
}
