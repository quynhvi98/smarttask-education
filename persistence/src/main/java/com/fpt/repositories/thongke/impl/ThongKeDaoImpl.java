package com.fpt.repositories.thongke.impl;

import com.fpt.entity.ThongKe;
import com.fpt.repositories.thongke.ThongKeDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ThongKeDaoImpl implements ThongKeDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ThongKe> getThongKe(String kiHoc) {
        List<ThongKe> lstThongKe = new ArrayList<>();
        String sql = "select  v.ten_vien,\n" +
                "    bm.ten_nganh,\n" +
                "    (select count(giao_vien.ma_giao_vien) from giao_vien where h.ma_nganh = bm.ma_nganh) as slgiaovien,\n" +
                "    (select count(h2.ma_lop) from lop_hoc where h.ma_mon_hoc = h.ma_mon_hoc) as slphonghoc,\n" +
                "    (select sum(h.tin_chi) from mon_hoc where h.ma_ki = " + kiHoc + ") as sltinchi\n" +
                "    from bo_mon bm\n" +
                "    join khoa_vien v on bm.ma_vien = v.ma_vien\n" +
                "    join mon_hoc h on bm.ma_nganh = h.ma_nganh\n" +
                "    join ki_hoc kh on h.ma_ki = kh.id\n" +
                "    join lop_hoc h2 on h.ma_mon_hoc = h2.ma_mon_hoc\n" +
                "    join phong_hoc ph on h2.phong_hoc = ph.id\n" +
                "    where ma_ki = " + kiHoc;
        Query query = entityManager.createNativeQuery(sql);
        List<Object> lstObj = query.getResultList();
        try {
            for (int i = 0; i < lstObj.size(); i++) {
                Object[] obj = (Object[]) lstObj.get(i);
                ThongKe thongKe = new ThongKe((String) obj[0], (String) obj[1], ((BigInteger) obj[2]).intValue(), null, ((BigInteger) obj[3]).intValue(), ((BigDecimal) obj[4]).intValue());
                lstThongKe.add(thongKe);
            }
        } catch (NullPointerException e) {
            return null;
        }
        return lstThongKe;
    }
}
