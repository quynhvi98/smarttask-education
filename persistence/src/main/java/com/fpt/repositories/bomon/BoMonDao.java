package com.fpt.repositories.bomon;

import com.fpt.entity.BoMon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoMonDao extends CrudRepository<BoMon, String>, BoMonDaoCustom{

}
