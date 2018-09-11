package com.fpt.entity;

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

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_lop", nullable=false)
    private Set<BaiViet> maBaiViet;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_lop", nullable=false)
    private Set<Nhom> maNhom;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "lop_sinhvien",
            joinColumns = { @JoinColumn(name = "ma_lop") },
            inverseJoinColumns = { @JoinColumn(name = "ma_sinh_vien") }
    )
    Set<SinhVien> sinhViens;

    public LopHoc() {
    }

    public Set<BaiViet> getMaBaiViet() {
        return maBaiViet;
    }

    public void setMaBaiViet(Set<BaiViet> maBaiViet) {
        this.maBaiViet = maBaiViet;
    }

    public Set<Nhom> getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(Set<Nhom> maNhom) {
        this.maNhom = maNhom;
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
}
