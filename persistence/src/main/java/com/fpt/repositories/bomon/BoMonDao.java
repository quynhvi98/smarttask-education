package com.fpt.repositories.bomon;

import com.fpt.entity.BoMon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoMonDao extends CrudRepository<BoMon, String>, BoMonDaoCustom{
    @Query(value = "from BoMon bm where bm.khoaVien.maVien = :maVien", nativeQuery = false)
    List<BoMon> getLstBoMonByMaVien(@Param("maVien") String khoaVien);
}
