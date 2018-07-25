package com.example.meituanmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meituanmvp.R;
import com.example.meituanmvp.pojo.HeaderViewBean;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<HeaderViewBean> list;

    public GridViewAdapter(Context context, List<HeaderViewBean> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 判断集合里的数据是否够能充满本页？
     * 能 返回每页可显示的最大值  mPageSize
     * 不能 则有多少数据显示多少 mPageSize:(list.size()-mIndex*mPageSize)
     * @return
     */
    @Override
    public int getCount() {
        return list.size();
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
        Log.i("TAG", "position:" + position);
        ViewHolder holder = null;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.item_grideview,null);
            holder = new ViewHolder();
            holder.iv=convertView.findViewById(R.id.iv_grid_img);
            holder.tv=convertView.findViewById(R.id.tv_gride_title);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        /**
         * 给view绑定数据室计算正确的下标
         *  position+mIndex*mPageSize
         */
        holder.iv.setImageResource(list.get(position).getImg());
        holder.tv.setText(list.get(position).getTitle());
        return convertView;
    }
    class ViewHolder{
        public TextView tv;
        public ImageView iv;
    }
}
