package com.fpt.services.diem;

import com.fpt.entity.Diem;
import com.fpt.entity.GiaoVien;

import java.util.List;

public interface DiemService {
  void careate(List<Diem> diem);
Diem save(Diem diem);
}
