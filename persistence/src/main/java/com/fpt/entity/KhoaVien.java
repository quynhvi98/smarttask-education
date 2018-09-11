package com.fpt.entity;

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

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_vien", nullable=false)
    private Set<BoMon> tenNganh;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_vien", nullable=false)
    private Set<SinhVien> maSinhVien;

    public Set<SinhVien> getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(Set<SinhVien> maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public void setTenNganh(Set<BoMon> tenNganh) {
        this.tenNganh = tenNganh;
    }

    private Set<BoMon> getTenNganh(){
        return getTenNganh();
    };

    public KhoaVien() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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
}
