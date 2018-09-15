package com.fpt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "ngay_tao")
    private Date ngay_tao;

    @JsonIgnore
    @OneToMany(mappedBy = "baiViet")
    private Set<BaiTap> lstBaiTap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_loai")
    private LoaiBaiViet loaiBaiViet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_lop")
    private LopHoc lopHoc;

    public BaiViet() {
    }

    public Set<BaiTap> getLstBaiTap() {
        return lstBaiTap;
    }

    public void setLstBaiTap(Set<BaiTap> lstBaiTap) {
        this.lstBaiTap = lstBaiTap;
    }

    public LoaiBaiViet getLoaiBaiViet() {
        return loaiBaiViet;
    }

    public void setLoaiBaiViet(LoaiBaiViet loaiBaiViet) {
        this.loaiBaiViet = loaiBaiViet;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
