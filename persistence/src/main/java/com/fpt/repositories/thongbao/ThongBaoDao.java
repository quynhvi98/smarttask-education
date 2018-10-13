package com.fpt.repositories.thongbao;

import com.fpt.entity.ThongBao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThongBaoDao extends CrudRepository<ThongBao, String> {
    @Query(value = "select * from thong_bao join giao_vien v on thong_bao.ma_giao_vien = v.ma_giao_vien where v.user_name=:us ORDER BY time DESC",nativeQuery = true)
    List<ThongBao> listThongBaoGv(@Param("us") String us);

    @Query(value = "select * from thong_bao where thong_bao.id=:id_tb",nativeQuery = true)
    ThongBao thongBaoById(@Param("id_tb") String id_tb);

    @Query(value = "SELECT * FROM thong_bao tb join giao_vien v on tb.ma_giao_vien = v.ma_giao_vien where v.ma_giao_vien=:ma_gv ORDER BY tb.time DESC LIMIT 5",nativeQuery = true)
    List<ThongBao> thongBaoMoiNhatGV(@Param("ma_gv") String ma_gv);

    @Query(value = "SELECT * FROM thong_bao tb join sinh_vien v on tb.ma_sinh_vien = v.ma_sinh_vien where v.ma_sinh_vien=:ma_sv ORDER BY tb.time DESC LIMIT 5",nativeQuery = true)
    List<ThongBao> thongBaoMoiNhatSV(@Param("ma_sv") String ma_sv);

    @Query(value = "SELECT * FROM thong_bao tb join sinh_vien v on tb.ma_sinh_vien = v.ma_sinh_vien where v.ma_sinh_vien=:ma_sv and tb.status='false' ORDER BY tb.time DESC LIMIT 5",nativeQuery = true)
    List<ThongBao> soLuongTbChuaXemSV(@Param("ma_sv") String ma_sv);

    @Query(value = "SELECT * FROM thong_bao tb join giao_vien v on tb.ma_giao_vien = v.ma_giao_vien where v.ma_giao_vien=:ma_gv and tb.status='false' ORDER BY tb.time DESC LIMIT 5",nativeQuery = true)
    List<ThongBao> soLuongTbChuaXemGV(@Param("ma_gv") String ma_gv);

    @Query(value = "select * from thong_bao join sinh_vien v on thong_bao.ma_sinh_vien = v.ma_sinh_vien where v.user_name=:us ORDER BY time DESC",nativeQuery = true)
    List<ThongBao> listThongBaoSV(@Param("us") String us);

}
