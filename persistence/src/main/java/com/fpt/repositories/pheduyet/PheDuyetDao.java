package com.fpt.repositories.pheduyet;


import com.fpt.entity.LopHoc;
import com.fpt.entity.MonHoc;
import com.fpt.entity.PheDuyet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PheDuyetDao extends CrudRepository<PheDuyet, String> {
    @Query(value = "select * from phe_duyet ",nativeQuery = true)
    List<PheDuyet> listPheDuyet();
}
