package com.example.meituanmvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meituanmvp.R;
import com.example.meituanmvp.pojo.HeaderViewBean;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> implements View.OnClickListener {
    Context context;
    List<HeaderViewBean> list;
    int mIndex;
    int mPageSize;
    onItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context, List<HeaderViewBean> list, int mIndex,RecyclerViewAdapter.onItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.mIndex = mIndex;
        this.onItemClickListener = onItemClickListener;
        mPageSize = context.getResources().getInteger(R.integer.HomePageHeaderColumn)*2;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_grideview,null);
        view.setOnClickListener(this);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        int pos = position+mIndex*mPageSize;
        holder.itemView.setTag(pos);
        holder.iv.setImageResource(list.get(pos).getImg());
        holder.tv.setText(list.get(pos).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size()>(mIndex+1)*mPageSize?mPageSize:(list.size()-mIndex*mPageSize);
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, (Integer) v.getTag());
    }

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    class Holder extends RecyclerView.ViewHolder{
        public TextView tv;
        public ImageView iv;
        public Holder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_gride_title);
            iv = itemView.findViewById(R.id.iv_grid_img);
        }
    }
}
