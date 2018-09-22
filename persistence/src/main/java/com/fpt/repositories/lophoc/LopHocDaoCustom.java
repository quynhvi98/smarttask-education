package com.fpt.repositories.lophoc;

import java.util.List;

public interface LopHocDaoCustom {
    List<String[]> getSchedule(String maGiaoVien, String kiHoc);
}
