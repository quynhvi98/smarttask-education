package com.fpt.repositories.monhoc;

import com.fpt.entity.LopHoc;
import com.fpt.entity.MonHoc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonHocDao extends CrudRepository<MonHoc, String> {
    @Query(value = "select * from mon_hoc mh join ki_hoc h on mh.ma_ki = h.id where h.ki=:hocky" ,nativeQuery = true)
    List<MonHoc> listMonHocKy(@Param("hocky") String hocky);

}
