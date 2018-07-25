package com.example.meituanmvp.pojo;

public class HeaderViewBean {
    private Integer img;
    private String title;

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HeaderViewBean(Integer img, String title) {
        this.img = img;
        this.title = title;
    }

    public HeaderViewBean() {
        super();
    }

    @Override
    public String toString() {
        return "HeaderViewBean{" +
                "img=" + img +
                ", title='" + title + '\'' +
                '}';
    }
}
