package com.fpt.services.bomon;

import com.fpt.entity.BoMon;
import com.fpt.entity.Role;

import java.util.List;

public interface BoMonService {
    BoMon taoBoMon(BoMon boMon);
    List<BoMon> findAll();
    BoMon findById(String boMon);
    List<BoMon> searchBoMon(String tenBoMon,String maBoMon);
    void update(BoMon persist);

    List<BoMon> getLstBoMonByMaVien(String khoaVien);
}
