package com.fpt.repositories.lophoc;

import java.util.List;

public interface LopHocDaoCustom {
    List<Object[]> getSchedule(String maGiaoVien, String kiHoc);

}
