package com.fpt.services.baitaplon;

import com.fpt.entity.BaiTapLon;

public interface BaiTapLonService {
    BaiTapLon create(BaiTapLon baiTapLon);
    BaiTapLon findByMaLop(String  maLop);

}
