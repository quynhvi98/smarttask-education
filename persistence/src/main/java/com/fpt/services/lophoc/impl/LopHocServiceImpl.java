package com.fpt.services.lophoc.impl;

import com.fpt.entity.LopHoc;
import com.fpt.repositories.lophoc.LopHocDao;
import com.fpt.services.lophoc.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LopHocServiceImpl implements LopHocService {
    @Autowired
    private LopHocDao lopHocDao;

    @Override
    public LopHoc taoLopHoc(LopHoc lopHoc) {
        return lopHocDao.save(lopHoc);
    }
}
