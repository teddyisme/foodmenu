package com.lixinsheng.foodmenu.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixinsheng.foodmenu.R;
import com.lixinsheng.foodmenu.app.AppApplication;
import com.lixinsheng.foodmenu.bean.Classify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixinsheng on 16/2/16.
 */
public class SearchAdpater extends BaseAdapter {
    private ArrayList<Classify> search_list;

    protected LayoutInflater inflater;

    public SearchAdpater(ArrayList<Classify> search_list) {
        this.search_list = search_list;
        inflater= LayoutInflater.from(AppApplication.get());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHodler holdView;
        if(convertView==null){
            holdView = new ViewHodler();
            convertView = inflater.inflate(R.layout.search_list_item, null);
            holdView.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holdView);
        }else{
            holdView = (ViewHodler) convertView.getTag();
        }
        String name =search_list.get(position).getName();

        holdView.textView.setText(name);

        return convertView;
    }

    class ViewHodler {
        TextView textView;
    }

    @Override
    public int getCount() {
        return search_list.size();
    }

    @Override
    public Object getItem(int position) {
        return search_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
