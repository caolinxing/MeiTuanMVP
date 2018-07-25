package com.example.meituanmvp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideOption;
import com.example.meituanmvp.R;
import com.example.meituanmvp.pojo.NearShopBean;

import org.w3c.dom.Text;

import java.util.List;

public class MyHomeListAdapter extends BaseAdapter {
    private Context context;
    private List<NearShopBean.DataBean> list;

    public MyHomeListAdapter(Context context, List<NearShopBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.item_shop,null);
            holder = new ViewHolder();
            holder.item_iv_jian = convertView.findViewById(R.id.item_iv_jian);
            holder.item_iv_shop_icon = convertView.findViewById(R.id.item_iv_shop_icon);
            holder.item_iv_zhe = convertView.findViewById(R.id.item_iv_zhe);
            holder.item_lucheng = convertView.findViewById(R.id.item_lucheng);
            holder.item_ratin_bar = convertView.findViewById(R.id.item_ratin_bar);
            holder.item_title = convertView.findViewById(R.id.item_title);
            holder.item_tv_jian = convertView.findViewById(R.id.item_tv_jian);
            holder.item_tv_peisongjia = convertView.findViewById(R.id.item_tv_peisongjia);
            holder.item_tv_zhe = convertView.findViewById(R.id.item_tv_zhe);
            holder.item_xiaoliang = convertView.findViewById(R.id.item_xiaoliang);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getPic_url()).into(holder.item_iv_shop_icon);
        holder.item_xiaoliang.setText(list.get(position).getMonth_sales_tip());
        holder.item_lucheng.setText(list.get(position).getDelivery_time_tip()+"/"+list.get(position).getDistance());
        holder.item_tv_peisongjia.setText(list.get(position).getMin_price_tip()+
                list.get(position).getShipping_fee_tip()+list.get(position).getAverage_price_tip());
        Glide.with(context).load(list.get(position).getDiscounts2().get(0).getIcon_url()).into(holder.item_iv_jian);
        Glide.with(context).load(list.get(position).getDiscounts2().get(1).getIcon_url()).into(holder.item_iv_zhe);
        holder.item_tv_jian.setText(list.get(position).getDiscounts2().get(0).getInfo());
        holder.item_tv_zhe.setText(list.get(position).getDiscounts2().get(1).getInfo());
        holder.item_title.setText(list.get(position).getName());
        holder.item_ratin_bar.setRating((float) list.get(position).getWm_poi_score());
        return convertView;
    }
    class ViewHolder{
        ImageView item_iv_shop_icon;
        RatingBar item_ratin_bar;
        TextView item_title;
        TextView item_tv_peisongjia;
        TextView item_xiaoliang;
        TextView item_lucheng;
        TextView item_tv_jian;
        ImageView item_iv_jian;
        ImageView item_iv_zhe;
        TextView item_tv_zhe;
    }
}
