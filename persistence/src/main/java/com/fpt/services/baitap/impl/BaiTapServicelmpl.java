package com.fpt.services.baitap.impl;

import com.fpt.entity.BaiTap;
import com.fpt.repositories.baidang.BaiDangDao;
import com.fpt.repositories.baitap.BaiTapDao;
import com.fpt.services.baitap.BaiTapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaiTapServicelmpl implements BaiTapService {
    @Autowired
    private BaiTapDao baiTapDao;
    @Override
    public BaiTap create(BaiTap baiTap) {
        return baiTapDao.save(baiTap);
    }

    @Override
    public BaiTap findBySVAndLop(String maSV, String maLop) {
        return baiTapDao.baiTapBySinhVienAndLopHoc(maSV,maLop);
    }

    @Override
    public BaiTap findById(Integer id) {
        return baiTapDao.findOne(id);
    }
}
