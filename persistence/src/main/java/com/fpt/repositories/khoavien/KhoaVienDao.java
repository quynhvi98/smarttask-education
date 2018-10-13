package com.fpt.repositories.khoavien;

import com.fpt.entity.KhoaVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KhoaVienDao extends CrudRepository<KhoaVien, String> {
    @Query(value = "select * from khoa_vien k where k.ma_vien like concat('%',:maVien,'%') and k.ten_vien like concat('%',:tenVien,'%')", nativeQuery = true)
    List<KhoaVien> search(@Param("tenVien") String tenVien,@Param("maVien") String maVien);

}
