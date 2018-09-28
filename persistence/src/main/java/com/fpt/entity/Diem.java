package com.fpt.entity;

import javax.persistence.*;

@Entity
@Table(name = "diem_sinhvien")
public class Diem {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @Column(name = "diem_ly_thuyet")
    private Double diemLyThuyet;

    @Column(name = "diem_thuc_hanh")
    private Double diemThucHanh;

    @Column(name = "diem_cuoi_ki")
    private Double diemCuoiKi;

    @ManyToOne
    @JoinColumn(name = "ma_sinh_vien")
    private SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "ma_lop")
    private LopHoc lopHoc;

    @ManyToOne
    @JoinColumn(name = "ma_giao_vien")
    private GiaoVien giaoVien;

    @ManyToOne
    @JoinColumn(name = "ma_mon_hoc")
    private MonHoc monHoc;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDiemLyThuyet() {
        return diemLyThuyet;
    }

    public void setDiemLyThuyet(Double diemLyThuyet) {
        this.diemLyThuyet = diemLyThuyet;
    }

    public Double getDiemThucHanh() {
        return diemThucHanh;
    }

    public void setDiemThucHanh(Double diemThucHanh) {
        this.diemThucHanh = diemThucHanh;
    }

    public Double getDiemCuoiKi() {
        return diemCuoiKi;
    }

    public void setDiemCuoiKi(Double diemCuoiKi) {
        this.diemCuoiKi = diemCuoiKi;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }
}
