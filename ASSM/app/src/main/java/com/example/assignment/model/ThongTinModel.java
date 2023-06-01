package com.example.assignment.model;

public class ThongTinModel implements java.io.Serializable{
    //id: id thông tin primary key, code: mã môn học, date: ngày học, room: phòng học
    private int id;
    private String code;
    private String date;
    private String room;

    public ThongTinModel(int id, String code, String date, String room) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
