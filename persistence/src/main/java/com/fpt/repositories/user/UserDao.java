package com.fpt.repositories.user;

import com.fpt.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends CrudRepository<User, String> {
    @Query("from User u where u.userName = :username")
    User findUserByUserName(@Param("username") String username);
}
