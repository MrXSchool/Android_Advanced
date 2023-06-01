package com.example.myapplication;

public class spModel implements java.io.Serializable {
    private String name;
    private String gia;

    public spModel(String name, String gia) {
        this.name = name;
        this.gia = gia;
    }

    public String getName() {
        return name;
    }

    public String getGia() {
        return gia;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
