package com.fpt.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Posted from Sep 11, 2018, 10:33 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "nop_de_tai")
public class NopDeTai {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "ngay_nop")
    private Date ngayNop;

    @Column(name = "path")
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nhom")
    private Nhom nhom;

    public NopDeTai() {
    }

    public Nhom getNhom() {
        return nhom;
    }

    public void setNhom(Nhom nhom) {
        this.nhom = nhom;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayNop() {
        return ngayNop;
    }

    public void setNgayNop(Date ngayNop) {
        this.ngayNop = ngayNop;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
