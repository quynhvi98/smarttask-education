package com.fpt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Posted from Sep 11, 2018, 10:18 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "lop_hoc")
public class LopHoc {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_lop")
    private String maLop;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "phong_hoc")
    private String phongHoc;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;

    @OneToMany(mappedBy = "lopHoc", fetch = FetchType.EAGER)
    private Set<BaiViet> lstBaiViet;

    @OneToMany(mappedBy = "lopHoc", fetch = FetchType.EAGER)
    private Set<Nhom> lstNhom;

    @OneToMany(mappedBy = "lopHoc", fetch = FetchType.EAGER)
    private Set<PheDuyet> lstPheDuyet;

    @ManyToOne
    @JoinColumn(name = "giaoVien")
    private GiaoVien giaoVien;

    @ManyToOne
    @JoinColumn(name = "ma_mon_hoc")
    private MonHoc monHoc;

    @OneToMany(mappedBy = "lopHoc", fetch = FetchType.EAGER)
    private Set<ThongBao> lstThongBao;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "lop_sinhvien",
            joinColumns = { @JoinColumn(name = "ma_lop") },
            inverseJoinColumns = { @JoinColumn(name = "ma_sinh_vien") }
    )
    Set<SinhVien> sinhViens;

    public LopHoc() {
    }

    public Set<PheDuyet> getLstPheDuyet() {
        return lstPheDuyet;
    }

    public void setLstPheDuyet(Set<PheDuyet> lstPheDuyet) {
        this.lstPheDuyet = lstPheDuyet;
    }

    public Set<BaiViet> getLstBaiViet() {
        return lstBaiViet;
    }

    public void setLstBaiViet(Set<BaiViet> lstBaiViet) {
        this.lstBaiViet = lstBaiViet;
    }

    public Set<Nhom> getLstNhom() {
        return lstNhom;
    }

    public void setLstNhom(Set<Nhom> lstNhom) {
        this.lstNhom = lstNhom;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public Set<ThongBao> getLstThongBao() {
        return lstThongBao;
    }

    public void setLstThongBao(Set<ThongBao> lstThongBao) {
        this.lstThongBao = lstThongBao;
    }

    public Set<SinhVien> getSinhViens() {
        return sinhViens;
    }

    public void setSinhViens(Set<SinhVien> sinhViens) {
        this.sinhViens = sinhViens;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
