package com.example.meituanmvp.main.shop_detail.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.meituanmvp.main.shop_detail.pojo.ModelBean;

public class MyMutlipleItem implements MultiItemEntity {
    public static final int FIRST_TYPE = 1;
    public static final int SECOND_TYPE = 2;
    private int itemType;
    private ModelBean data;

    public MyMutlipleItem(int itemType, ModelBean data) {
        this.itemType = itemType;
        this.data = data;
    }


    @Override
    public int getItemType() {
        return itemType;
    }

    public ModelBean getData(){
        return data;
    }
}
