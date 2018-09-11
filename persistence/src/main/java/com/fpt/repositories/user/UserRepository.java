package com.fpt.repositories.user;

import com.fpt.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "from User u where u.userName =: username", nativeQuery = false)
    User findByUsername(@Param("username") String email);
}
