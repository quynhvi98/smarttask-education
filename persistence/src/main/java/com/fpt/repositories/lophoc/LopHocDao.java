package com.fpt.repositories.lophoc;

import com.fpt.entity.LopHoc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LopHocDao extends CrudRepository<LopHoc, String> {
    @Query(value = "select * from lop_hoc ",nativeQuery = true)
    List<LopHoc> listLopHoc();

    @Query(value = "select * from lop_hoc  lh join giao_vien v on lh.giaoVien = v.ma_giao_vien join mon_hoc mh join user u on v.user_name = u.user_name where u.full_name like :gv and mh.ten_mon_hoc like :mh" ,nativeQuery = true)
    List<LopHoc> listSearchGiaoVien(@Param("gv") String gv,@Param("mh") String mh);

    @Query(value = "select * from lop_hoc lh join mon_hoc mh where lh.ma_lop like :lop and mh.ten_mon_hoc like :mh",nativeQuery = true)
    List<LopHoc> listSearchLop(@Param("lop") String lop,@Param("mh") String mh);

}
