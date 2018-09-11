package com.fpt.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mon_hoc")
public class MonHoc {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_mon_hoc")
    private String maMonHoc;

    @Column(name = "ten_mon_hoc")
    private String tenMonHoc;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="ma_mon_hoc", nullable=false)
    private Set<LopHoc> maLopHoc;

    public MonHoc() {
    }

    public Set<LopHoc> getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(Set<LopHoc> maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }
}
