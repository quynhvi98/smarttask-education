package com.fpt.services.bomon;

import com.fpt.entity.BoMon;
import com.fpt.entity.Role;

import java.util.List;

public interface BoMonService {
    BoMon taoBoMon(BoMon boMon);
    List<BoMon> findAll();
    BoMon findById(String boMon);
}
