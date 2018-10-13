package com.fpt.repositories.user;

import com.fpt.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends CrudRepository<User, String> {
    @Query("from User u where u.userName = :username")
    User findUserByUserName(@Param("username") String username);

    @Query(value = "select * from user u left join giao_vien v on u.user_name = v.user_name left join sinh_vien v2 on u.user_name = v2.user_name " +
            "where v.ma_giao_vien like  concat('%',:maThanhVien,'%') or v2.ma_sinh_vien like  concat('%',:maThanhVien,'%') and u.full_name like  concat('%',:fullName,'%') and u.user_name like  concat('%',:userName,'%')" ,nativeQuery = true)
    List<User> search(@Param("userName") String userName, @Param("maThanhVien") String maThanhVien, @Param("fullName") String fullName);
}
