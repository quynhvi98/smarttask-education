package com.fpt.services.pheduyetlop.impl;


import com.fpt.entity.PheDuyet;
import com.fpt.repositories.pheduyet.PheDuyetDao;
import com.fpt.services.pheduyetlop.PheDuyetLopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PheDuyetLopServiceImpl implements PheDuyetLopService {

    @Autowired
    private PheDuyetDao pheDuyetDao;

    @Override
    public List<PheDuyet> listPheDuyet() {
        return pheDuyetDao.listPheDuyet();
    }

    @Override
    public PheDuyet createPheDuyet(PheDuyet pheDuyet) {
        return pheDuyetDao.save(pheDuyet);
    }

    @Override
    public List<PheDuyet> listPheDuyetTheoSV(String username) {
        return pheDuyetDao.listPheDuyetTheoSinhVien(username);
    }


}
