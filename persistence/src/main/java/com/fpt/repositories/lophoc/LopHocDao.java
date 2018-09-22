package com.fpt.repositories.lophoc;

import com.fpt.entity.LopHoc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LopHocDao extends CrudRepository<LopHoc, String>, LopHocDaoCustom {
    @Query(value = "select * from lop_hoc ",nativeQuery = true)
    List<LopHoc> listLopHoc();

    @Query(value = "select * from lop_hoc  lh join giao_vien v on lh.giaoVien = v.ma_giao_vien join mon_hoc mh join user u on v.user_name = u.user_name where u.full_name like concat('%',:gv,'%') and mh.ten_mon_hoc like  concat('%',:mh,'%')" ,nativeQuery = true)
    List<LopHoc> listSearchGiaoVien(@Param("gv") String gv,@Param("mh") String mh);

    @Query(value = "select * from lop_hoc lh join mon_hoc mh where lh.ma_lop like concat('%',:lop,'%') and mh.ten_mon_hoc like concat('%',:mh,'%')",nativeQuery = true)
    List<LopHoc> listSearchLop(@Param("lop") String lop,@Param("mh") String mh);

    @Query(value = "select * from lop_hoc join lop_sinhvien ls on lop_hoc.ma_lop = ls.ma_lop where ls.ma_sinh_vien=:msv",nativeQuery = true)
    List<LopHoc> listLopHocSV(@Param("msv") String msv);

    @Query(value = "select * from lop_hoc  lh join lop_sinhvien ls on lh.ma_lop = ls.ma_lop where ls.ma_sinh_vien=:masv and lh.ma_lop=:malop" ,nativeQuery = true)
    LopHoc getLopHocSv(@Param("malop") String malop,@Param("masv") String masv);

    @Query(value = "select * from lop_hoc join lop_sinhvien ls on lop_hoc.ma_lop = ls.ma_lop join mon_hoc h on lop_hoc.ma_mon_hoc = h.ma_mon_hoc where ls.ma_sinh_vien =:msv and  h.ma_mon_hoc=:mmh" ,nativeQuery = true)
    LopHoc getLopHocSvBm(@Param("msv") String msv,@Param("mmh") String mmh);


}
