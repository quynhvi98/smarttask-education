package com.fpt.repositories.baidang;

import com.fpt.entity.BaiDang;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaiDangDao extends CrudRepository<BaiDang, Integer> {
    @Query("from BaiDang bd where bd.lopHoc.maLop = :maLop")
    List<BaiDang> findByMaLop(@Param("maLop") String maLop);
}
