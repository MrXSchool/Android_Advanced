package com.example.assignment.model.sample2;

public class Sample2_image {
    //"url": "https://s.vnecdn.net/vnexpress/i/v20/logos/vne_logo_rss.png",
    //		"title": "Tin nhanh VnExpress - Đọc báo, tin tức online 24h",
    //		"link": "https://vnexpress.net"

    private String url;
    private String title;
    private String link;

    public Sample2_image(String url, String title, String link) {
        this.url = url;
        this.title = title;
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
