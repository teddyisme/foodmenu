package com.lixinsheng.foodmenu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lixinsheng.foodmenu.R;
import com.lixinsheng.foodmenu.bean.Classify;
import com.lixinsheng.foodmenu.commen.Utills;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lixinsheng on 16/2/15.
 */
public class ClassifyAdapter extends RecyclerView.Adapter {
    private List<Classify> classifies;

    private List<Integer> colors ;

    private View itemView;
    public ClassifyAdapter(List<Classify> classifies) {
        this.classifies = classifies;
        colors = Arrays.asList(R.color.red,R.color.green,R.color.blue,R.color.orang);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_item0, null);
        }else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_item1, null);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);
        return new ClassifyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewholder, int position) {
        ClassifyViewHolder holder = (ClassifyViewHolder) viewholder;
        holder.position = position;
        Classify person = classifies.get(position);
        holder.nameTv.setText(person.getName());
        if(position<4){
            holder.nameTv.setBackgroundColor(Utills.getResourceColor(colors.get(position)));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position<4){
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return classifies.size();
    }

    class ClassifyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        public View rootView;
        public TextView nameTv;
        public int position;

        public ClassifyViewHolder(View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.classify_list_root);
            nameTv = (TextView) itemView.findViewById(R.id.nameTv);

            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return null != onRecyclerViewListener && onRecyclerViewListener.onItemLongClick(position);
        }
    }

    public interface OnRecyclerViewListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}
