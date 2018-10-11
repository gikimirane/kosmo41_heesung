package com.study.andriod.project5.노선;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.study.andriod.project5.정류소.St_Item;
import com.study.andriod.project5.즐겨찾기.Fr1_ItemView;

import java.util.ArrayList;

public class bRid_Adapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<bRid_Item> items = new ArrayList<>();


    public bRid_Adapter(Context context){
        this.context = context;
    }

    public void addItem(bRid_Item item){ items.add(item);}

    public void clearItem(){ items.clear();}

    @Override
    public int getCount(){return  items.size();}

    @Override
    public Object getItem(int position){ return items.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){



        bRid_ItemView view = null;
        if(convertView == null){
            view = new bRid_ItemView(context);
        }else {
            view = (bRid_ItemView) convertView;
        }

        bRid_Item item = items.get(position);
        view.setstationNm(item.getStationNm());
        view.setbeginTm(item.getBeginTm());
        view.setlastTm(item.getLastTm());
        view.setarsId(item.getArsId());
        view.setImageView(item.getEquls());

        return view;
    }
}
