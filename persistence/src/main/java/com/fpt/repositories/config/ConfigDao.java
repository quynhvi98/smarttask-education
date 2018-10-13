package com.fpt.repositories.config;

import com.fpt.entity.Config;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ConfigDao  extends CrudRepository<Config, String> {
    @Query(value = "select * from config cf where cf.config_name=:name",nativeQuery = true)
    Config findByName(@Param("name") String name);
}
