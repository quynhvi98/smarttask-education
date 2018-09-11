package com.fpt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String userPassWord;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_email")
    private String userEmal;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_avatar")
    private String userAvatar;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_dob")
    private String userDOB;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="user_name", nullable=false)
    private Set<GiaoVien> maGiaoVien;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="user_name", nullable=false)
    private Set<SinhVien> maSinhVien;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "phan_quyen",
            joinColumns = { @JoinColumn(name = "user_name") },
            inverseJoinColumns = { @JoinColumn(name = "ma_quyen") }
    )
    Set<Role> roles;

    public User() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getUserEmal() {
        return userEmal;
    }

    public void setUserEmal(String userEmal) {
        this.userEmal = userEmal;
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

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public Set<GiaoVien> getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(Set<GiaoVien> maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public Set<SinhVien> getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(Set<SinhVien> maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

}