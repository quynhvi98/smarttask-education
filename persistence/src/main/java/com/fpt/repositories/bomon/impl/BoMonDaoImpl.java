package com.fpt.repositories.bomon.impl;

import com.fpt.entity.BoMon;
import com.fpt.repositories.bomon.BoMonDao;
import com.fpt.repositories.bomon.BoMonDaoCustom;
import com.fpt.repositories.khoavien.KhoaVienDao;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BoMonDaoImpl implements BoMonDaoCustom {
    @Autowired
    KhoaVienDao khoaVienDao;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<BoMon> getAll() {
        List<BoMon> lstBomon = em.createQuery("from BoMon bm").getResultList();
        Hibernate.initialize(lstBomon);
        for(BoMon boMon : lstBomon){
            boMon.getKhoaVien();
        }
        return lstBomon;
    }
}
