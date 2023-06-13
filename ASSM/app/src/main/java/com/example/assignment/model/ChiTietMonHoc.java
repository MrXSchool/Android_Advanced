package com.example.assignment.model;

public class ChiTietMonHoc {
    //code1, name, teacher, date array, room
    private String code1;
    private String name;
    private String teacher;
    private String date;
    private String room;

    public ChiTietMonHoc(String code1, String name, String teacher, String date, String room) {
        this.code1 = code1;
        this.name = name;
        this.teacher = teacher;
        this.date = date;
        this.room = room;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
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
