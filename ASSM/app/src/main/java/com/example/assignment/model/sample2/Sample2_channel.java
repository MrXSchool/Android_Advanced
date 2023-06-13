package com.example.assignment.model.sample2;

import java.util.ArrayList;

public class Sample2_channel {

   //"title": "Giáo dục - VnExpress RSS",
    //    "description": "VnExpress RSS",
    //image":{}
    //"pubDate": "Tue, 04 Oct 2022 08:34:26 +0700",
    //    "generator": "VnExpress",
    //    "link": "https://vnexpress.net/rss/giao-duc.rss",
    //    "item": []

    private String title;
    private String description;
    private Sample2_image image;
    private String pubDate;
    private String generator;
    private String link;
    private ArrayList<Sample2_item> item;

    public Sample2_channel(String title, String description, Sample2_image image, String pubDate, String generator, String link, ArrayList<Sample2_item> item) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.pubDate = pubDate;
        this.generator = generator;
        this.link = link;
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sample2_image getImage() {
        return image;
    }

    public void setImage(Sample2_image image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<Sample2_item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Sample2_item> item) {
        this.item = item;
    }
}
