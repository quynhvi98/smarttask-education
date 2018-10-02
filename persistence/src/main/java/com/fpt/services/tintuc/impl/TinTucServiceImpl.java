package com.fpt.services.tintuc.impl;

import com.fpt.entity.TinTuc;
import com.fpt.repositories.tintuc.TinTucDao;
import com.fpt.services.tintuc.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TinTucServiceImpl implements TinTucService {
    @Autowired
    private TinTucDao tinTucDao;

    @Override
    public void save(TinTuc tinTuc) {
        tinTucDao.save(tinTuc);
    }

    @Override
    public List<TinTuc> findAllAvailable() {
        return tinTucDao.findAllAvailable();
    }

    @Override
    public TinTuc findById(Integer id) {
        return tinTucDao.findOne(id);
    }
}
