package com.fpt.services.thongbao;

import com.fpt.entity.ThongBao;

import java.util.List;

public interface ThongBaoService {
    ThongBao themThongBao(ThongBao thongBao);
    List<ThongBao> listThongBaoGv(String username);
    ThongBao findById(String id);
    List<ThongBao> thongBaoMoiNhat(String ma_gv);
}
