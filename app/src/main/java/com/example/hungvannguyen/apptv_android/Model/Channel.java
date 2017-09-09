package com.example.hungvannguyen.apptv_android.Model;

/**
 * Created by HungVanNguyen on 9/7/2017.
 */

public class Channel {
    String title;
    String logo;
    String link;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Channel() {
    }

    public Channel(String title, String logo) {
        this.title = title;
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
