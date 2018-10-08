package com.fpt.services.baitap;

import com.fpt.entity.BaiTap;

import java.util.List;

public interface BaiTapService {
    BaiTap create(BaiTap baiTap);
    BaiTap findBySVAndLop(String maSV,String maLop);
    BaiTap findById(Integer id);
    List<BaiTap> listBtAndLop(String maLop);
}
