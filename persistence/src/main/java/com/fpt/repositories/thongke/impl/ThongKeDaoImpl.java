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
                "    (select count(*) from sinh_vien sv where sv.ma_nganh = bm.ma_nganh" +
                "    and date_sub(date_sub(now(), interval "+6*(Integer.parseInt(kiHoc)-1)+" month ), INTERVAL "+6*Integer.parseInt(kiHoc)+" MONTH) <= sv.ngay_nhap_hoc) as slsinhvien,\n" +
                "    (select count(h2.ma_lop) from lop_hoc where h.ma_mon_hoc = h.ma_mon_hoc) as slphonghoc,\n" +
                "    (select sum(h.tin_chi) from mon_hoc where h.ma_ki = "+kiHoc+") as sltinchi\n" +
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
                ThongKe thongKe = new ThongKe((String) obj[0], (String) obj[1], ((BigInteger) obj[2]).intValue(),  ((BigInteger) obj[3]).intValue(), ((BigInteger) obj[4]).intValue(), ((BigDecimal) obj[5]).intValue());
                lstThongKe.add(thongKe);
            }
        } catch (NullPointerException e) {
            return null;
        }
        return lstThongKe;
    }

    @Override
    public List<Object> getLstLopByMaGiaoVien(String maGv) {
        String sql = "select ten_mon_hoc, ma_lop, ten_phong, ngay_hoc, ca_hoc " +
                "from lop_hoc " +
                "join giao_vien v on lop_hoc.ma_giao_vien = v.ma_giao_vien " +
                "join mon_hoc h on lop_hoc.ma_mon_hoc = h.ma_mon_hoc " +
                "join phong_hoc ph on lop_hoc.phong_hoc = ph.id " +
                "where v.ma_giao_vien = '"+maGv+"'";
        Query query = entityManager.createNativeQuery(sql);
        List<Object> lstObj = query.getResultList();
        return lstObj;
    }

    @Override
    public List<Object> getLstLopByMaSinhVien(String id) {
        String sql = "select h2.ten_mon_hoc, h.ma_lop, ph.ten_phong, h.ngay_hoc, h.ca_hoc " +
                "from lop_sinhvien ls " +
                "join lop_hoc h on ls.ma_lop = h.ma_lop " +
                "join sinh_vien v on ls.ma_sinh_vien = v.ma_sinh_vien " +
                "join mon_hoc h2 on h.ma_mon_hoc = h2.ma_mon_hoc " +
                "join phong_hoc ph on h.phong_hoc = ph.id " +
                "where v.ma_sinh_vien = '"+id+"'";
        Query query = entityManager.createNativeQuery(sql);
        List<Object> lstObj = query.getResultList();
        return lstObj;
    }
}
