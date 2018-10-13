package com.fpt.services.like;

import com.fpt.entity.Like;

import java.util.List;

public interface LikeService {
    void save(Like like);
    void delete(Integer likeId);
    String doLike(String userName, Integer postId);

    List<Like> getLikeByUser(String userName);
}
