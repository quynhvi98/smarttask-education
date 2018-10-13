package com.fpt.repositories.baitaplon;

import com.fpt.entity.BaiTapLon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaiTapLonDao extends CrudRepository<BaiTapLon, String> {
    @Query("from BaiTapLon btl where btl.lopHoc.maLop=:maLop")
    BaiTapLon findByMaLop(@Param("maLop") String maLop);

}
