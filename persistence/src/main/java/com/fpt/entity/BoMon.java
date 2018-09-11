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

    @Column(name = "ten_ngang")
    private String tenNganh;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_nganh", nullable=false)
    private Set<MonHoc> tenMonHoc;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_nganh", nullable=false)
    private Set<GiaoVien> maGiaoVien;



    public Set<GiaoVien> getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(Set<GiaoVien> maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }


    private Set<MonHoc> getTenMonHoc(){
        return getTenMonHoc();
    };

    public void setTenMonHoc(Set<MonHoc> tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public BoMon() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

}
