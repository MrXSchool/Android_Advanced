package com.example.assignment.model.sample2;

public class Sample2_item {

    //"title": "Quản lý trường mầm non bị tố bỏ đói, bạo hành trẻ",
    //		  "description": {
    //			"__cdata": "<a href=\"https://vnexpress.net/quan-ly-truong-mam-non-bi-to-bo-doi-bao-hanh-tre-4518723.html\"><img src=\"https://i1-vnexpress.vnecdn.net/2022/10/03/-2774-1664795211.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=lD4_qBW5VWLseAnb6riS8g\" ></a></br>Quản lý nhóm lớp tư thục Elm School được cho là đã kéo lê, đút cơm thô bạo, để trẻ chịu đói, khát."
    //		  },
    //		  "pubDate": "Tue, 04 Oct 2022 07:54:52 +0700",
    //		  "link": "https://vnexpress.net/quan-ly-truong-mam-non-bi-to-bo-doi-bao-hanh-tre-4518723.html",
    //		  "guid": "https://vnexpress.net/quan-ly-truong-mam-non-bi-to-bo-doi-bao-hanh-tre-4518723.html",
    //		  "comments": 0
    private String title;
    private Sample2_description description;
    private String pubDate;
    private String link;
    private String guid;
    private int comments;

    public Sample2_item(String title, Sample2_description description, String pubDate, String link, String guid, int comments) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
        this.guid = guid;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Sample2_description getDescription() {
        return description;
    }

    public void setDescription(Sample2_description description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
