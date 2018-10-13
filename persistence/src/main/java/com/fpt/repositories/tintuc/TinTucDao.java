package com.fpt.repositories.tintuc;

import com.fpt.entity.TinTuc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TinTucDao extends CrudRepository<TinTuc, Integer> {
    @Query("from TinTuc t where t.status <> 0")
    List<TinTuc> findAllAvailable();
}
