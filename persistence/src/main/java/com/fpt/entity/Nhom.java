package com.fpt.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Posted from Sep 11, 2018, 10:29 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "nhom")
public class Nhom {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_nhom")
    private String maNhom;

    @Column(name = "ten_nhom")
    private String tenNhom;

    @Column(name = "ngay_tao_nhom")
    private Date ngayTaoNhom;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_nhom", nullable=false)
    private Set<NopDeTai> maNopDeTai;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "chi_tiet_nhom_de_tai",
            joinColumns = { @JoinColumn(name = "ma_nhom") },
            inverseJoinColumns = { @JoinColumn(name = "ma_de_tai") }
    )
    Set<DeTai> deTais;

    @ManyToMany(mappedBy = "nhoms")
    private Set<SinhVien> sinhViens;

    public Nhom() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }

    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }

    public Date getNgayTaoNhom() {
        return ngayTaoNhom;
    }

    public void setNgayTaoNhom(Date ngayTaoNhom) {
        this.ngayTaoNhom = ngayTaoNhom;
    }

    public Set<NopDeTai> getMaNopDeTai() {
        return maNopDeTai;
    }

    public void setMaNopDeTai(Set<NopDeTai> maNopDeTai) {
        this.maNopDeTai = maNopDeTai;
    }

    public Set<DeTai> getDeTais() {
        return deTais;
    }

    public void setDeTais(Set<DeTai> deTais) {
        this.deTais = deTais;
    }

    public Set<SinhVien> getSinhViens() {
        return sinhViens;
    }

    public void setSinhViens(Set<SinhVien> sinhViens) {
        this.sinhViens = sinhViens;
    }
}
