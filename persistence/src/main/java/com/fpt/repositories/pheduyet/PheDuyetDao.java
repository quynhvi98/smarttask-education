package com.fpt.repositories.pheduyet;


import com.fpt.entity.LopHoc;
import com.fpt.entity.MonHoc;
import com.fpt.entity.PheDuyet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PheDuyetDao extends CrudRepository<PheDuyet, String> {
    @Query(value = "select * from phe_duyet ",nativeQuery = true)
    List<PheDuyet> listPheDuyet();

    @Query(value = " select * from phe_duyet join sinh_vien v on phe_duyet.ma_sinh_vien = v.ma_sinh_vien where v.user_name=:us_name",nativeQuery = true)
    List<PheDuyet> listPheDuyetTheoSinhVien(@Param("us_name") String us_name);

   }
