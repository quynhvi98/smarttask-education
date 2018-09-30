package com.fpt.repositories.tailieu;

import com.fpt.entity.TaiLieu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaiLieuDao extends CrudRepository<TaiLieu, Integer> {
    @Query("from TaiLieu tl where tl.lopHoc.maLop = :maLop")
    List<TaiLieu> findAllByMaLop(@Param("maLop") String maLop);
}
