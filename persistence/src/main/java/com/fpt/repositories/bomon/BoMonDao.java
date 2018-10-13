package com.fpt.repositories.bomon;

import com.fpt.entity.BoMon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoMonDao extends CrudRepository<BoMon, String>, BoMonDaoCustom{
    @Query(value = "from BoMon bm where bm.khoaVien.maVien = :maVien", nativeQuery = false)
    List<BoMon> getLstBoMonByMaVien(@Param("maVien") String khoaVien);

    @Query(value = "select * from bo_mon bm where bm.ten_nganh like concat('%',:tenNganh,'%') and bm.ma_nganh like concat('%',:maNganh,'%')", nativeQuery = true)
    List<BoMon> search(@Param("tenNganh") String tenNganh,@Param("maNganh") String maNganh);
}
