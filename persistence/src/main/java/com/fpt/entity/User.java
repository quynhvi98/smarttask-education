package com.fpt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String userPassWord;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_avatar")
    private String userAvatar;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_dob")
    private Date userDOB;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private GiaoVien giaoVien;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SinhVien sinhVien;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "phan_quyen",
            joinColumns = { @JoinColumn(name = "user_name") },
            inverseJoinColumns = { @JoinColumn(name = "ma_quyen") }
    )
    Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<BaiDang> lstBaiDang;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<BinhLuan> lstBinhLuan;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Like> lstLike;

    public User() {
    }

    public User(String userName, String userPassWord, String fullName, String userEmail, String userPhone, String userAddress, String userGender, Date userDOB) {
        this.userName = userName;
        this.userPassWord = userPassWord;
        this.fullName = fullName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userGender = userGender;
        this.userDOB = userDOB;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Date getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(Date userDOB) {
        this.userDOB = userDOB;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<BaiDang> getLstBaiDang() {
        return lstBaiDang;
    }

    public void setLstBaiDang(Set<BaiDang> lstBaiDang) {
        this.lstBaiDang = lstBaiDang;
    }

    public Set<BinhLuan> getLstBinhLuan() {
        return lstBinhLuan;
    }

    public void setLstBinhLuan(Set<BinhLuan> lstBinhLuan) {
        this.lstBinhLuan = lstBinhLuan;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<Like> getLstLike() {
        return lstLike;
    }

    public void setLstLike(Set<Like> lstLike) {
        this.lstLike = lstLike;
    }
}