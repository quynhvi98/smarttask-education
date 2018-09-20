package com.fpt.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Posted from Sep 20, 2018, 10:16 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Entity
@Table(name = "ki_hoc")
public class KiHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "ki")
    private String kiHoc;

    @OneToMany(mappedBy = "kiHoc", fetch = FetchType.EAGER)
    private Set<MonHoc> lstMonHoc;

}
