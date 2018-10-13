package com.fpt.repositories.systemlog;


import com.fpt.entity.SystemLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SystemLogDao extends CrudRepository<SystemLog, Integer> {
    @Query("from SystemLog sl order by sl.time desc ")
    List<SystemLog> findAllSort();
}
