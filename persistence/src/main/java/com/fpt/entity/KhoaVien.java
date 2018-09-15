package com.fpt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "khoa_vien")
public class KhoaVien implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_vien")
    private String maVien;

    @Column(name = "ten_vien")
    private String tenVien;

    @JsonIgnore
    @OneToMany(mappedBy = "khoaVien")
    private Set<BoMon> lstBoMon;

    @JsonIgnore
    @OneToMany(mappedBy = "khoaVien")
    private Set<SinhVien> lstSinhVien;

    public String getMaVien() {
        return maVien;
    }

    public void setMaVien(String maVien) {
        this.maVien = maVien;
    }

    public String getTenVien() {
        return tenVien;
    }

    public void setTenVien(String tenVien) {
        this.tenVien = tenVien;
    }

    public Set<BoMon> getLstBoMon() {
        return lstBoMon;
    }

    public void setLstBoMon(Set<BoMon> lstBoMon) {
        this.lstBoMon = lstBoMon;
    }

    public Set<SinhVien> getLstSinhVien() {
        return lstSinhVien;
    }

    public void setLstSinhVien(Set<SinhVien> lstSinhVien) {
        this.lstSinhVien = lstSinhVien;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
