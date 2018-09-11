package com.fpt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Posted from Sep 11, 2018, 10:32 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "de_tai")
public class DeTai {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_de_tai")
    private String maDeTai;

    @Column(name = "ten_de_tai")
    private String tenDeTai;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_de_tai", nullable=false)
    private Set<NopDeTai> maNopDeTai;

    @ManyToMany(mappedBy = "deTais")
    private Set<Nhom> nhoms;

    public DeTai() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaDeTai() {
        return maDeTai;
    }

    public void setMaDeTai(String maDeTai) {
        this.maDeTai = maDeTai;
    }

    public String getTenDeTai() {
        return tenDeTai;
    }

    public void setTenDeTai(String tenDeTai) {
        this.tenDeTai = tenDeTai;
    }

    public Set<NopDeTai> getMaNopDeTai() {
        return maNopDeTai;
    }

    public void setMaNopDeTai(Set<NopDeTai> maNopDeTai) {
        this.maNopDeTai = maNopDeTai;
    }

    public Set<Nhom> getNhoms() {
        return nhoms;
    }

    public void setNhoms(Set<Nhom> nhoms) {
        this.nhoms = nhoms;
    }
}
