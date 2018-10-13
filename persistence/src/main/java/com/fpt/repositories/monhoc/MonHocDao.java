package com.fpt.repositories.monhoc;

import com.fpt.entity.MonHoc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonHocDao extends CrudRepository<MonHoc, String> {
    @Query(value = "select * from mon_hoc mh join ki_hoc h on mh.ma_ki = h.id join bo_mon m on mh.ma_nganh = m.ma_nganh join khoa_vien v on m.ma_vien = v.ma_vien join bo_mon m2 on mh.ma_nganh = m2.ma_nganh join  khoa_vien kv on m.ma_vien = kv.ma_vien where h.ki=:hocKi and m.ma_vien=:mv" ,nativeQuery = true)
    List<MonHoc> listMonHocKy(@Param("hocKi") int hocki,@Param("mv") String mv);

    @Query(value = "from MonHoc mh where mh.boMon.maNganh = :maNganh and mh.kiHoc.id = :maKi", nativeQuery = false)
    List<MonHoc> getLstMonHocByHocKiAndBoMon(@Param("maNganh") String boMon, @Param("maKi") Integer kiHoc);

    @Query(value = "select * from mon_hoc mh where mh.ten_mon_hoc like concat('%',:tenMonHoc,'%') and mh.ma_mon_hoc like concat('%',:maMonHoc,'%')", nativeQuery = true)
    List<MonHoc> search(@Param("tenMonHoc") String tenMonHoc,@Param("maMonHoc") String maMonHoc);
}
