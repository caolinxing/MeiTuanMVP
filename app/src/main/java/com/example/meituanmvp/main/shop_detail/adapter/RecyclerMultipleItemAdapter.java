package com.example.meituanmvp.main.shop_detail.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meituanmvp.R;

import java.util.List;

public class RecyclerMultipleItemAdapter extends BaseMultiItemQuickAdapter<MyMutlipleItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RecyclerMultipleItemAdapter(List data) {
        super(data);
        addItemType(MyMutlipleItem.FIRST_TYPE, R.layout.item_recycler_right_layout_1);
        addItemType(MyMutlipleItem.SECOND_TYPE, R.layout.item_recycler_right_layout_2);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMutlipleItem item) {
        switch (helper.getItemViewType()){
            case MyMutlipleItem.FIRST_TYPE:
                helper.setText(R.id.item_recy1_tv_title,item.getData().getName())
                        .setText(R.id.item_recy1_price,item.getData().getPrice())
                        .setText(R.id.item_recy1_taocan,item.getData().getTaocan())
                        .setText(R.id.item_recy1_xiaoliang,item.getData().getXaioliang());

                Glide.with(mContext).load(item.getData().getPic_url()).into((ImageView)helper.getView(R.id.item_recy1_iv_img));
                break;
            case MyMutlipleItem.SECOND_TYPE:
                helper.setText(R.id.item_recy2_price,item.getData().getPrice())
                        .setText(R.id.item_recy2_taocan,item.getData().getTaocan())
                        .setText(R.id.item_recy2_xiaoliang,item.getData().getXaioliang());
                Glide.with(mContext).load(item.getData().getPic_url()).into((ImageView)helper.getView(R.id.item_recy2_iv_img));
                break;
        }
    }
}
