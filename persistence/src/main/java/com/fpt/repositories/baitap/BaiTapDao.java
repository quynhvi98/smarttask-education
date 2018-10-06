package com.fpt.repositories.baitap;

import com.fpt.entity.BaiTap;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaiTapDao extends CrudRepository<BaiTap, Integer> {
    @Query(value = "select * from bai_tap bt where bt.ma_sinh_vien=:maSV and bt.ma_lop=:maLop", nativeQuery = true)
    BaiTap baiTapBySinhVienAndLopHoc(@Param("maSV") String maSV, @Param("maLop") String maLop);
}
