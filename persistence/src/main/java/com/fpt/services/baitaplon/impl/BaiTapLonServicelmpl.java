package com.fpt.services.baitaplon.impl;

import com.fpt.entity.BaiTapLon;
import com.fpt.repositories.baitaplon.BaiTapLonDao;
import com.fpt.services.baitaplon.BaiTapLonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaiTapLonServicelmpl implements BaiTapLonService {
    @Autowired
    BaiTapLonDao baiTapLonDao;

    @Override
    public BaiTapLon create(BaiTapLon baiTapLon) {
        return baiTapLonDao.save(baiTapLon);
    }
}
