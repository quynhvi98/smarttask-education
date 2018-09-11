package com.fpt.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "config")
public class Config implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "config_type")
    private String configType;

    @Column(name = "config_name")
    private String configName;

    public Config() {
    }

    public Config(String configType, String configName) {
        this.configType = configType;
        this.configName = configName;
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

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }
}
