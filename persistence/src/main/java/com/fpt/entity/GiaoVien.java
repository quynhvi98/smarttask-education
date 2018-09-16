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
@Table(name = "giao_vien")
public class GiaoVien {
    @Id
    @Column(name = "ma_giao_vien")
    private String maGiaoVien;

    @Column(name = "hoc_ham")
    private String hocHam;

    @Column(name = "mo_ta")
    private String moTa;

    @OneToMany(mappedBy = "giaoVien", fetch = FetchType.EAGER)
    private Set<ThongBao> lstThongBao;

    @OneToMany(mappedBy = "giaoVien", fetch = FetchType.EAGER)
    private Set<LopHoc> lstLopHoc;

    @OneToOne
    @JoinColumn(name="user_name")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ma_nganh")
    private BoMon boMon;

    public GiaoVien() {
    }

    public Set<ThongBao> getLstThongBao() {
        return lstThongBao;
    }

    public void setLstThongBao(Set<ThongBao> lstThongBao) {
        this.lstThongBao = lstThongBao;
    }

    public Set<LopHoc> getLstLopHoc() {
        return lstLopHoc;
    }

    public void setLstLopHoc(Set<LopHoc> lstLopHoc) {
        this.lstLopHoc = lstLopHoc;
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
