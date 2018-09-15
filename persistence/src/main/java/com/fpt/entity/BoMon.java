package com.fpt.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_vien")
    private KhoaVien khoaVien;

    @OneToMany(mappedBy = "boMon")
    private Set<MonHoc> lstMonHoc;

    @OneToMany(mappedBy = "boMon")
    private Set<GiaoVien> lstGiaoVien;

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

    public Set<GiaoVien> getLstGiaoVien() {
        return lstGiaoVien;
    }

    public void setLstGiaoVien(Set<GiaoVien> lstGiaoVien) {
        this.lstGiaoVien = lstGiaoVien;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
