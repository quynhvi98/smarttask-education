package com.fpt.services.binhluan.impl;

import com.fpt.entity.BinhLuan;
import com.fpt.repositories.binhluan.BinhLuanDao;
import com.fpt.services.binhluan.BinhLuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BinhLuanServiceImpl implements BinhLuanService {
    @Autowired
    private BinhLuanDao binhLuanDao;

    @Override
    public void save(BinhLuan binhLuan) {
        binhLuanDao.save(binhLuan);
    }
}
