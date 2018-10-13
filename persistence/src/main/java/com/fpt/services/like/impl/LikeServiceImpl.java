package com.fpt.services.like.impl;

import com.fpt.entity.BaiDang;
import com.fpt.entity.Like;
import com.fpt.entity.User;
import com.fpt.repositories.baidang.BaiDangDao;
import com.fpt.repositories.like.LikeDao;
import com.fpt.repositories.user.UserDao;
import com.fpt.services.baidang.BaiDangService;
import com.fpt.services.like.LikeService;
import com.fpt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeDao likeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    BaiDangDao baiDangDao;

    @Override
    public void save(Like like) {
        likeDao.save(like);
    }

    @Override
    public void delete(Integer likeId) {
        likeDao.delete(likeId);
    }

    @Override
    public String doLike(String userName, Integer postId) {
        Like likeTemp = likeDao.getLikeByUserNameAndPostId(userName, postId);
        if(likeTemp == null){
            User user = userDao.findUserByUserName(userName);
            BaiDang baiDang = baiDangDao.findOne(postId);
            if(user != null && postId != null){
                Like like = new Like();
                like.setBaiDang(baiDang);
                like.setUser(user);
                this.save(like);
                return "like";
            }
        }else {
            this.delete(likeTemp.getLikeId());
            return "unlike";
        }
        return null;
    }

    @Override
    public List<Like> getLikeByUser(String userName) {
        return likeDao.getLikeByUser(userName);
    }
}
