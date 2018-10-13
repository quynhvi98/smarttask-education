package com.fpt.services.tailieu.impl;

import com.fpt.entity.TaiLieu;
import com.fpt.repositories.tailieu.TaiLieuDao;
import com.fpt.services.tailieu.TaiLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiLieuServiceImpl implements TaiLieuService {
    @Autowired
    TaiLieuDao taiLieuDao;

    @Override
    public void save(TaiLieu taiLieu) {
        taiLieuDao.save(taiLieu);
    }

    @Override
    public List<TaiLieu> findAllByMaLop(String maLop) {
        return taiLieuDao.findAllByMaLop(maLop);
    }

    @Override
    public TaiLieu findById(Integer docId) {
        return taiLieuDao.findOne(docId);
    }
}
