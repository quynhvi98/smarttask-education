package com.fpt.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Posted from Sep 11, 2018, 10:23 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "bai_viet")
public class BaiViet {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_bai_viet")
    private String maBaiViet;

    @Column(name = "ten_bai_viet")
    private String tenBaiViet;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "ngay_tao")
    private Date ngay_tao;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_bai_viet", nullable=false)
    private Set<BaiTap> maBaiTap;

    public BaiViet() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaBaiViet() {
        return maBaiViet;
    }

    public void setMaBaiViet(String maBaiViet) {
        this.maBaiViet = maBaiViet;
    }

    public String getTenBaiViet() {
        return tenBaiViet;
    }

    public void setTenBaiViet(String tenBaiViet) {
        this.tenBaiViet = tenBaiViet;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public Set<BaiTap> getMaBaiTap() {
        return maBaiTap;
    }

    public void setMaBaiTap(Set<BaiTap> maBaiTap) {
        this.maBaiTap = maBaiTap;
    }


}
