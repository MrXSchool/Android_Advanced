package com.example.assignment.model;

import java.util.ArrayList;

public class Sample1_all {
    private String title;
    private ArrayList<Sample1_data> data;

    public Sample1_all(String title, ArrayList<Sample1_data> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Sample1_data> getData() {
        return data;
    }

    public void setData(ArrayList<Sample1_data> data) {
        this.data = data;
    }
}
