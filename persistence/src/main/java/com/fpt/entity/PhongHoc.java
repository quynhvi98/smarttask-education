package com.fpt.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "phong_hoc")
public class PhongHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten_phong")
    private String tenPhong;

    @OneToMany(mappedBy = "phongHoc", fetch = FetchType.EAGER)
    private Set<LopHoc> lopHocs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public Set<LopHoc> getLopHocs() {
        return lopHocs;
    }

    public void setLopHocs(Set<LopHoc> lopHocs) {
        this.lopHocs = lopHocs;
    }
}
