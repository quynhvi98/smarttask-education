package com.fpt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Posted from Sep 10, 2018, 7:41 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "sinh_vien")
public class SinhVien {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_sinh_vien")
    private String maSinhVien;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_sinh_vien", nullable=false)
    private Set<BaiTap> maBaiTap;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "chi_tiet_nhom",
            joinColumns = { @JoinColumn(name = "ma_sinh_vien") },
            inverseJoinColumns = { @JoinColumn(name = "ma_nhom") }
    )
    Set<Nhom> nhoms;

    @ManyToMany(mappedBy = "sinhViens")
    private Set<LopHoc> lopHocs;

    public SinhVien() {
    }

    public Set<BaiTap> getMaBaiTap() {
        return maBaiTap;
    }

    public void setMaBaiTap(Set<BaiTap> maBaiTap) {
        this.maBaiTap = maBaiTap;
    }

    public Set<Nhom> getNhoms() {
        return nhoms;
    }

    public void setNhoms(Set<Nhom> nhoms) {
        this.nhoms = nhoms;
    }

    public Set<LopHoc> getLopHocs() {
        return lopHocs;
    }

    public void setLopHocs(Set<LopHoc> lopHocs) {
        this.lopHocs = lopHocs;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }
}
