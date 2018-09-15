package com.fpt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "bo_mon")
public class BoMon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_nganh")
    private String maNganh;

    @Column(name = "ten_nganh")
    private String tenNganh;

    @Column(name = "trang_thai")
    private String trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_vien")
    private KhoaVien khoaVien;

    @JsonIgnore
    @OneToMany(mappedBy = "boMon")
    private Set<MonHoc> lstMonHoc;

    @JsonIgnore
    @OneToMany(mappedBy = "boMon")
    private Set<GiaoVien> lstGiaoVien;

    public Set<GiaoVien> getLstGiaoVien() {
        return lstGiaoVien;
    }

    public void setLstGiaoVien(Set<GiaoVien> lstGiaoVien) {
        this.lstGiaoVien = lstGiaoVien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public KhoaVien getKhoaVien() {
        return khoaVien;
    }

    public void setKhoaVien(KhoaVien khoaVien) {
        this.khoaVien = khoaVien;
    }

    public Set<MonHoc> getLstMonHoc() {
        return lstMonHoc;
    }

    public void setLstMonHoc(Set<MonHoc> lstMonHoc) {
        this.lstMonHoc = lstMonHoc;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
