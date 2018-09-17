package com.fpt.entity;

import javax.persistence.*;

/**
 * Posted from Sep 17, 2018, 10:05 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "config")
public class PheDuyet {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "ma_giao_vien")
    private GiaoVien giaoVien;

    @ManyToOne
    @JoinColumn(name = "ma_lop")
    private LopHoc lopHoc;

    @ManyToOne
    @JoinColumn(name = "ma_sinh_vien")
    private SinhVien sinhVien;

    public PheDuyet() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }
}
