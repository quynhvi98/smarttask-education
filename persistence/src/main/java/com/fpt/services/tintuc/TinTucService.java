package com.fpt.services.tintuc;

import com.fpt.entity.TinTuc;

import java.util.List;

public interface TinTucService {
    void save(TinTuc tinTuc);
    List<TinTuc> findAllAvailable();
    TinTuc findById(Integer id);

    void delete(Integer id);
}
