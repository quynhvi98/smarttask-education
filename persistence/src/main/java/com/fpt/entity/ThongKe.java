package com.fpt.entity;

public class ThongKe {
    private String tenVien;
    private String tenNganh;
    private Integer slGiaoVien;
    private Integer slSinhVien;
    private Integer slPhongHoc;
    private Integer slTinChi;

    public ThongKe(String tenVien, String tenNganh, Integer slGiaoVien, Integer slSinhVien, Integer slPhongHoc, Integer slTinChi) {
        this.tenVien = tenVien;
        this.tenNganh = tenNganh;
        this.slGiaoVien = slGiaoVien;
        this.slSinhVien = slSinhVien;
        this.slPhongHoc = slPhongHoc;
        this.slTinChi = slTinChi;
    }

    public String getTenVien() {
        return tenVien;
    }

    public void setTenVien(String tenVien) {
        this.tenVien = tenVien;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public Integer getSlGiaoVien() {
        return slGiaoVien;
    }

    public void setSlGiaoVien(Integer slGiaoVien) {
        this.slGiaoVien = slGiaoVien;
    }

    public Integer getSlSinhVien() {
        return slSinhVien;
    }

    public void setSlSinhVien(Integer slSinhVien) {
        this.slSinhVien = slSinhVien;
    }

    public Integer getSlPhongHoc() {
        return slPhongHoc;
    }

    public void setSlPhongHoc(Integer slPhongHoc) {
        this.slPhongHoc = slPhongHoc;
    }

    public Integer getSlTinChi() {
        return slTinChi;
    }

    public void setSlTinChi(Integer slTinChi) {
        this.slTinChi = slTinChi;
    }
}
