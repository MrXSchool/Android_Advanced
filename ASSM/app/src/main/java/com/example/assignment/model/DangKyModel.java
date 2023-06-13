package com.example.assignment.model;

public class DangKyModel implements java.io.Serializable{
    //id: id đăng ký primary key, idUser: id người dùng, idInfo: id thông tin
    private int id;
    private int idUser;
    private String Code;

    public DangKyModel(int id, int idUser, String code) {
        this.id = id;
        this.idUser = idUser;
        Code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
