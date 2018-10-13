package com.fpt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_quyen", nullable = false)
    private String maQuyen;

    @Column(name = "ten_quyen", nullable = false)
    private String tenQuyen;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}