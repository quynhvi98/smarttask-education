package com.fpt.services.diem;

import com.fpt.entity.Diem;
import com.fpt.entity.GiaoVien;

import java.util.List;

public interface DiemService {
  void careate(List<Diem> diem);
  Diem save(Diem diem);
  void delete(Diem diem);
  Diem  findById(String id);
  List<Diem> listDiemKi(String msv,int ki);
  List<Diem> listDiemSVandLop(String msv,String maLop);

}
