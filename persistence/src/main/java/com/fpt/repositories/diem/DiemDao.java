package com.fpt.repositories.diem;

import com.fpt.entity.Diem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiemDao  extends CrudRepository<Diem, String> {
    @Query(value = "select * from diem_sinhvien d join mon_hoc h on d.ma_mon_hoc = h.ma_mon_hoc join sinh_vien v on d.ma_sinh_vien = v.ma_sinh_vien\n" +
            "where d.ma_sinh_vien=:msv and h.ma_ki=:ki",nativeQuery = true)
    List<Diem> listDiemKi(@Param("msv") String msv,@Param("ki") int ki);

    @Query(value = "select * from diem_sinhvien d where d.ma_sinh_vien =:msv and d.ma_lop=:maLop",nativeQuery = true)
    List<Diem> listDiemSVandLop(@Param("msv") String msv,@Param("maLop")String maLop);


}
