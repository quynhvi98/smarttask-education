package com.fpt.repositories.sinhvien;

import com.fpt.entity.SinhVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SinhVienDao extends CrudRepository<SinhVien, String> {
    @Query(value = "select * from sinh_vien join user u on sinh_vien.user_name = u.user_name",nativeQuery = true)
    List<SinhVien> listSV();

    @Query(value = "select *from sinh_vien join lop_sinhvien ls on sinh_vien.ma_sinh_vien = ls.ma_sinh_vien\n" +
            "    join lop_hoc h on ls.ma_lop = h.ma_lop\n" +
            "                     left join diem_sinhvien sinhvien on h.ma_lop = sinhvien.ma_lop and ls.ma_sinh_vien=sinhvien.ma_sinh_vien\n" +
            "where ls.ma_lop=:mlh",nativeQuery = true)
    List<SinhVien> getListSinhVienbyLopHocId( @Param("mlh") String mlh);

    @Query(value = "select * from sinh_vien sv where sv.ngay_nhap_hoc>=:day1 and sv.ngay_nhap_hoc<=:day2",nativeQuery = true)
    List<SinhVien> listSVki( @Param("day1") String day1,@Param("day2") String day2);
}
