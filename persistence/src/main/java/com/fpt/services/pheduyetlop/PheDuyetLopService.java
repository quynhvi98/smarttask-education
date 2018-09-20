package com.fpt.services.pheduyetlop;

import com.fpt.entity.LopHoc;
import com.fpt.entity.MonHoc;
import com.fpt.entity.PheDuyet;

import java.util.List;

public interface PheDuyetLopService {
    List<PheDuyet> listPheDuyet();
    PheDuyet createPheDuyet(PheDuyet pheDuyet);
    List<PheDuyet> listPheDuyetTheoSV(String username);
}
