package com.fpt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Posted from Sep 11, 2018, 10:22 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "loai_bai_viet")
public class LoaiBaiViet {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_loai")
    private String maLoai;

    @Column(name = "ten_loai")
    private String tenLoai;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_loai", nullable=false)
    private Set<BaiViet> maBaiViet;

    public LoaiBaiViet() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Set<BaiViet> getMaBaiViet() {
        return maBaiViet;
    }

    public void setMaBaiViet(Set<BaiViet> maBaiViet) {
        this.maBaiViet = maBaiViet;
    }
}
