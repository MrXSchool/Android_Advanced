package com.example.assignment.model;

public class MonHocModel implements java.io.Serializable{
    //code: mã môn học primary key, name: tên môn học, teacher: giảng viên
    private String code;
    private String name;
    private String teacher;

    public MonHocModel(String code, String name, String teacher) {
        this.code = code;
        this.name = name;
        this.teacher = teacher;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
