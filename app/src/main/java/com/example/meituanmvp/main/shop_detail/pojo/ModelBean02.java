package com.example.meituanmvp.main.shop_detail.pojo;

import java.util.List;

public class ModelBean02 {
    private String pic_url;
    private String xaioliang;
    private String price;
    private String taocan;

    public ModelBean02() {
        super();
    }

    @Override
    public String toString() {
        return "ModelBean02{" +
                "pic_url='" + pic_url + '\'' +
                ", xaioliang='" + xaioliang + '\'' +
                ", price='" + price + '\'' +
                ", taocan='" + taocan + '\'' +
                '}';
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

    public ModelBean02(String pic_url, String xaioliang, String price, String taocan) {

        this.pic_url = pic_url;
        this.xaioliang = xaioliang;
        this.price = price;
        this.taocan = taocan;
    }
}
