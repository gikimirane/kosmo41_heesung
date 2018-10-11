package com.study.andriod.project6.즐겨찾기;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Fr1_Adapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<Fr1_Item> items = new ArrayList<>();


    public Fr1_Adapter(Context context){
        this.context = context;
    }

    public void addItem(Fr1_Item item){ items.add(item);}

    public void clearItem(){ items.clear();}

    @Override
    public int getCount(){return  items.size();}

    @Override
    public Object getItem(int position){ return items.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){



        Fr1_ItemView view = null;
        if(convertView == null){
            view = new Fr1_ItemView(context);
        }else {
            view = (Fr1_ItemView) convertView;
        }

        Fr1_Item item = items.get(position);
        view.setStName(item.getStName());
        view.setStNum(item.getStNum());

        return view;
    }
}
