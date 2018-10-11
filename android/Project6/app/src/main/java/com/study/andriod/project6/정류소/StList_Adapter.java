package com.study.andriod.project6.정류소;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class StList_Adapter extends BaseAdapter {

    Context context;
    ArrayList<StList_Item> items = new ArrayList<>();


    public StList_Adapter(Context context){
        this.context = context;
    }

    public void addItem(StList_Item item){ items.add(item);}

    public void clearItem(){ items.clear();}

    @Override
    public int getCount(){return  items.size();}

    @Override
    public Object getItem(int position){ return items.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){



        StList_ItemView view = null;
        if(convertView == null){
            view = new StList_ItemView(context);
        }else {
            view = (StList_ItemView) convertView;
        }

        StList_Item item = items.get(position);
        view.setBusName(item.getBusRouteId());
        view.setStNum(item.getStNum());

        return view;
    }
}
