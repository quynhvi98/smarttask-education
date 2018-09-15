package com.fpt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Posted from Sep 10, 2018, 7:41 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "sinh_vien")
public class SinhVien {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_sinh_vien")
    private String maSinhVien;

    @JsonIgnore
    @OneToMany(mappedBy = "sinhVien")
    private Set<BaiTap> lstBaiTap;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "chi_tiet_nhom",
            joinColumns = { @JoinColumn(name = "ma_sinh_vien") },
            inverseJoinColumns = { @JoinColumn(name = "ma_nhom") }
    )
    Set<Nhom> nhoms;

    @ManyToMany(mappedBy = "sinhViens")
    private Set<LopHoc> lopHocs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_vien")
    private KhoaVien khoaVien;

    @OneToOne(mappedBy = "sinhVien", fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "sinhVien")
    private Set<ThongBao> lstThongBao;

    public SinhVien() {
    }

    public Set<BaiTap> getLstBaiTap() {
        return lstBaiTap;
    }

    public void setLstBaiTap(Set<BaiTap> lstBaiTap) {
        this.lstBaiTap = lstBaiTap;
    }

    public Set<ThongBao> getLstThongBao() {
        return lstThongBao;
    }

    public void setLstThongBao(Set<ThongBao> lstThongBao) {
        this.lstThongBao = lstThongBao;
    }

    public Set<Nhom> getNhoms() {
        return nhoms;
    }

    public void setNhoms(Set<Nhom> nhoms) {
        this.nhoms = nhoms;
    }

    public Set<LopHoc> getLopHocs() {
        return lopHocs;
    }

    public void setLopHocs(Set<LopHoc> lopHocs) {
        this.lopHocs = lopHocs;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public KhoaVien getKhoaVien() {
        return khoaVien;
    }

    public void setKhoaVien(KhoaVien khoaVien) {
        this.khoaVien = khoaVien;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
