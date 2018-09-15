package com.fpt.entity;

import javax.persistence.*;

/**
 * Posted from Sep 15, 2018, 4:11 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "thong_bao")
public class ThongBao {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_giao_vien")
    private GiaoVien giaoVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_sinh_vien")
    private SinhVien sinhVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_lop")
    private LopHoc lopHoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nhom")
    private Nhom nhom;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }

    public Nhom getNhom() {
        return nhom;
    }

    public void setNhom(Nhom nhom) {
        this.nhom = nhom;
    }
}
