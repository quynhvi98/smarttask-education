package com.fpt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Posted from Sep 10, 2018, 7:41 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "giao_vien")
public class GiaoVien {
    @Id
    @Column(name = "ma_giao_vien")
    private String maGiaoVien;

    @Column(name = "hoc_ham")
    private String hocHam;

    @Column(name = "mo_ta")
    private String moTa;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_giao_vien", nullable=false)
    private Set<LopHoc> maLopHoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nganh")
    private BoMon boMon;

    @OneToOne(mappedBy = "giaoVien", fetch = FetchType.LAZY)
    private User user;

    public GiaoVien() {
    }

    public Set<LopHoc> getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(Set<LopHoc> maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public String getHocHam() {
        return hocHam;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public BoMon getBoMon() {
        return boMon;
    }

    public void setBoMon(BoMon boMon) {
        this.boMon = boMon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
