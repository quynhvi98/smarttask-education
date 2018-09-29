package com.fpt.services.baidang.impl;

import com.fpt.entity.BaiDang;
import com.fpt.repositories.baidang.BaiDangDao;
import com.fpt.services.baidang.BaiDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaiDangServiceImpl implements BaiDangService {
    @Autowired
    private BaiDangDao baiDangDao;

    @Override
    public void save(BaiDang baiDang) {
        baiDangDao.save(baiDang);
    }

    @Override
    public List<BaiDang> findAll() {
        return (List<BaiDang>) baiDangDao.findAllAvailable();
    }

    @Override
    public List<BaiDang> findByMaLop(String maLop) {
        List<BaiDang> lstBaiDang = baiDangDao.findByMaLop(maLop);
        for (BaiDang bd : lstBaiDang) {
            String suffix = null;
            if(bd.getFile() != null){
                suffix = bd.getFile().split("\\.")[1];
                bd.setImageSuffix(suffix);
            }
        }
        return lstBaiDang;
    }

    @Override
    public BaiDang findById(Integer postId) {
        return baiDangDao.findOne(postId);
    }

    @Override
    public void delete(Integer postId) {
        BaiDang baiDang = baiDangDao.findOne(postId);
        baiDang.setStatus(0);
        baiDangDao.save(baiDang);
    }
}
