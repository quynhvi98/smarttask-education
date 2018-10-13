package com.fpt.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bai_dang")
public class BaiDang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postid")
    private Integer postId;

    private String content;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_real_name")
    private String fileRealName;

    private Integer status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @OneToMany(mappedBy = "baiDang", fetch = FetchType.EAGER)
    private Set<BinhLuan> lstComment;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lop_hoc")
    private LopHoc lopHoc;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "baiDang")
    private Set<Like> lstLike;

    @Transient
    private String imageSuffix;
//    @OneToMany(mappedBy = "guideid", fetch = FetchType.EAGER)
//    private Set<Like> lstLike;


    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Set<BinhLuan> getLstComment() {
        return lstComment;
    }

    public void setLstComment(Set<BinhLuan> lstComment) {
        this.lstComment = lstComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImageSuffix() {
        return imageSuffix;
    }

    public void setImageSuffix(String imageSuffix) {
        this.imageSuffix = imageSuffix;
    }

    public Set<Like> getLstLike() {
        return lstLike;
    }

    public void setLstLike(Set<Like> lstLike) {
        this.lstLike = lstLike;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }
}
