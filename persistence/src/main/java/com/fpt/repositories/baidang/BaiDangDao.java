package com.fpt.repositories.baidang;

import com.fpt.entity.BaiDang;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaiDangDao extends CrudRepository<BaiDang, Integer> {
    @Query("from BaiDang bd where bd.lopHoc.maLop = :maLop and  bd.status <> 0 order by bd.time asc")
    List<BaiDang> findByMaLop(@Param("maLop") String maLop);

    @Query("from BaiDang bd where bd.status <> 0 order by bd.time asc ")
    List<BaiDang> findAllAvailable();
}
