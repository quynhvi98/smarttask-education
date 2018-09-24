package com.fpt.repositories.sinhvien;

import com.fpt.entity.SinhVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SinhVienDao extends CrudRepository<SinhVien, String> {
    @Query(value = "select * from sinh_vien join user u on sinh_vien.user_name = u.user_name",nativeQuery = true)
    List<SinhVien> listSV();

    @Query(value = "select * from sinh_vien join lop_sinhvien ls on sinh_vien.ma_sinh_vien = ls.ma_sinh_vien where ls.ma_lop = :mlh",nativeQuery = true)
    List<SinhVien> getListSinhVienbyLopHocId( @Param("mlh") String maGiaoVien);
}
