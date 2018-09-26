package com.fpt.repositories.phonghoc;

import com.fpt.entity.PhongHoc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhongHocDao extends CrudRepository<PhongHoc, Integer> {

}
