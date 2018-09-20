package com.fpt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mon_hoc")
public class MonHoc {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_mon_hoc")
    private String maMonHoc;

    @Column(name = "ten_mon_hoc")
    private String tenMonHoc;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "tin_chi")
    private int tinChi;

    @ManyToOne
    @JoinColumn(name = "ma_nganh")
    private BoMon boMon;

    @ManyToOne
    @JoinColumn(name = "ma_ki")
    private KiHoc kiHoc;

    @OneToMany(mappedBy = "monHoc", fetch = FetchType.EAGER)
    private Set<LopHoc> lstLopHoc;

    public MonHoc() {
    }

    public int getTinChi() {
        return tinChi;
    }

    public void setTinChi(int tinChi) {
        this.tinChi = tinChi;
    }

    public BoMon getBoMon() {
        return boMon;
    }

    public void setBoMon(BoMon boMon) {
        this.boMon = boMon;
    }

    public Set<LopHoc> getLstLopHoc() {
        return lstLopHoc;
    }

    public void setLstLopHoc(Set<LopHoc> lstLopHoc) {
        this.lstLopHoc = lstLopHoc;
    }


    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

}
