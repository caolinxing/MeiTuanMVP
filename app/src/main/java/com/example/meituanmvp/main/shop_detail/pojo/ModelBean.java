package com.example.meituanmvp.main.shop_detail.pojo;

public class ModelBean {
    private String name;
    private String pic_url;
    private String xaioliang;
    private String price;
    private String taocan;

    public ModelBean(String name, String pic_url, String xaioliang, String price, String taocan) {
        this.name = name;
        this.pic_url = pic_url;
        this.xaioliang = xaioliang;
        this.price = price;
        this.taocan = taocan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getXaioliang() {
        return xaioliang;
    }

    public void setXaioliang(String xaioliang) {
        this.xaioliang = xaioliang;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTaocan() {
        return taocan;
    }

    public void setTaocan(String taocan) {
        this.taocan = taocan;
    }

    @Override
    public String toString() {
        return "ModelBean{" +
                "name='" + name + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", xaioliang='" + xaioliang + '\'' +
                ", price='" + price + '\'' +
                ", taocan='" + taocan + '\'' +
                '}';
    }

    public ModelBean() {
        super();
    }
}
