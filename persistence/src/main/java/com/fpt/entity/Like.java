package com.fpt.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "likes")
public class Like  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "likeid")
    private Integer likeId;

    @ManyToOne
    @JoinColumn(name = "postid")
    private BaiDang baiDang;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public BaiDang getBaiDang() {
        return baiDang;
    }

    public void setBaiDang(BaiDang baiDang) {
        this.baiDang = baiDang;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
