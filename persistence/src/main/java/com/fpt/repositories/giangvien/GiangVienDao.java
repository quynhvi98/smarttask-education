package com.fpt.repositories.giangvien;

import com.fpt.entity.GiaoVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GiangVienDao extends CrudRepository<GiaoVien, String> {
    @Query(value = "from GiaoVien gv where gv.boMon.maNganh = :maNganh",nativeQuery = false)
    List<GiaoVien> getLstGiangVienByMaNganh(@Param("maNganh") String maNganh);
}
