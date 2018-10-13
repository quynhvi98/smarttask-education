package com.fpt.services.thongbao;

import com.fpt.entity.ThongBao;

import java.util.List;

public interface ThongBaoService {
    ThongBao themThongBao(ThongBao thongBao);
    List<ThongBao> listThongBaoGv(String username);
    List<ThongBao> listThongBaoSV(String username);
    ThongBao findById(String id);
    List<ThongBao> thongBaoMoiNhatGV(String ma_gv);
    List<ThongBao> thongBaoMoiNhatSV(String ma_sv);
    int soLuongTbChuaXemGV(String ma_gv);
    int soLuongTbChuaXemSV(String ma_sv);
}
