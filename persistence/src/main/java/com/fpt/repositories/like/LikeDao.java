package com.fpt.repositories.like;

import com.fpt.entity.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeDao extends CrudRepository<Like, Integer> {
    @Query("from Like l where l.user.userName = :userName and l.baiDang.postId = :postId")
    Like getLikeByUserNameAndPostId(@Param("userName") String userName, @Param("postId") Integer postId);
    @Query("from Like l where l.user.userName = :userName")
    List<Like> getLikeByUser(@Param("userName") String userName);
}
