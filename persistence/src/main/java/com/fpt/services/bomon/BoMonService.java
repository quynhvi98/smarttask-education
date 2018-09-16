package com.fpt.services.bomon;

import com.fpt.entity.BoMon;

import java.util.List;

public interface BoMonService {
    BoMon taoBoMon(BoMon boMon);
    List<BoMon> findAll();
}
