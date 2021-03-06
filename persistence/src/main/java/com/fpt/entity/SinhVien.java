package com.fpt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Posted from Sep 10, 2018, 7:41 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */

@JsonIgnoreProperties({"lstBaiTap", "lstPheDuyet","nhoms","lopHocs","khoaVien","lstThongBao"})
@Entity
@Table(name = "sinh_vien")
public class SinhVien {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_sinh_vien")
    private String maSinhVien;

    @Column(name = "ngay_nhap_hoc")
    private Date ngayNhapHoc;

    @OneToMany(mappedBy = "sinhVien", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<BaiTap> lstBaiTap;

    @ManyToMany(mappedBy = "sinhViens", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<LopHoc> lopHocs;

    @ManyToOne
    @JoinColumn(name = "ma_vien")
    @JsonIgnore
    private KhoaVien khoaVien;

    @ManyToOne
    @JoinColumn(name = "ma_nganh")
    private  BoMon boMon;

    @OneToOne
    @JoinColumn(name="user_name")
    private User user;

    @OneToMany(mappedBy = "sinhVien", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ThongBao> lstThongBao;

    @OneToMany(mappedBy = "sinhVien", fetch = FetchType.EAGER)
    private Set<Diem> lstDiem;


    public SinhVien() {
    }

    public Set<Diem> getLstDiem() {
        return lstDiem;
    }

    public void setLstDiem(Set<Diem> lstDiem) {
        this.lstDiem = lstDiem;
    }

    public BoMon getBoMon() {
        return boMon;
    }

    public void setBoMon(BoMon boMon) {
        this.boMon = boMon;
    }

    public Date getNgayNhapHoc() {
        return ngayNhapHoc;
    }

    public void setNgayNhapHoc(Date ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    public Set<BaiTap> getLstBaiTap() {
        return lstBaiTap;
    }

    public void setLstBaiTap(Set<BaiTap> lstBaiTap) {
        this.lstBaiTap = lstBaiTap;
    }

    public Set<ThongBao> getLstThongBao() {
        return lstThongBao;
    }

    public void setLstThongBao(Set<ThongBao> lstThongBao) {
        this.lstThongBao = lstThongBao;
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

    public KhoaVien getKhoaVien() {
        return khoaVien;
    }

    public void setKhoaVien(KhoaVien khoaVien) {
        this.khoaVien = khoaVien;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            SinhVien sinhVien = (SinhVien) object;
            if (this.maSinhVien.equals(sinhVien.getMaSinhVien())  ) {
                result = true;
            }
        }
        return result;
    }
}
