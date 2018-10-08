package com.fpt.repositories.thongke;

import com.fpt.entity.ThongKe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThongKeDao{
    List<ThongKe> getThongKe(String kiHoc);
}
