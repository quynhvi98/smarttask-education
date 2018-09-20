package com.fpt.repositories.sinhvien;

import com.fpt.entity.SinhVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SinhVienDao extends CrudRepository<SinhVien, String> {
    @Query(value = "select * from sinh_vien join user u on sinh_vien.user_name = u.user_name",nativeQuery = true)
    List<SinhVien> listSV();
}
