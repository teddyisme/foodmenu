package com.lixinsheng.foodmenu.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lixinsheng.foodmenu.R;
import com.lixinsheng.foodmenu.base.BaseRecycleAdapter;
import com.lixinsheng.foodmenu.bean.FoodMenu;

import java.util.ArrayList;

/**
 * Created by lixinsheng on 16/3/21.
 */
public class FoodMenuAdpater extends BaseRecycleAdapter {

    public FoodMenuAdpater(ArrayList list, Context mContext) {
        super(list, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_item, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.position = position;
        FoodMenu foodMenu = (FoodMenu) list.get(position);
        String img_url = foodMenu.getImg();

        Glide
                .with(mContext)
                .load(img_url)
                .centerCrop()
                .crossFade()
                .into(viewHolder.img);
        viewHolder.nameTv.setText(foodMenu.getName());
    }

    class ViewHolder extends RecyclerViewHolderBase {
        public View rootView;
        public TextView nameTv;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.foodmenu_root);
            nameTv = (TextView) itemView.findViewById(R.id.nameTv);
            img = (ImageView) itemView.findViewById(R.id.food_img);

            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }
    }
}
