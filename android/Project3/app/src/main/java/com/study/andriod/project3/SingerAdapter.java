package com.study.andriod.project3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SingerAdapter extends BaseAdapter {

    Context context;
    ArrayList<SingerItem> items = new ArrayList<>();


    public  SingerAdapter(Context context){
        this.context = context;
    }
    public void addItem(SingerItem item){ items.add(item);}

    public void clearItem(){ items.clear();}

    @Override
    public int getCount(){return  items.size();}

    @Override
    public Object getItem(int position){ return items.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){



        SingerItemView view = null;
        if(convertView == null){
            view = new SingerItemView(context);
        }else {
            view = (SingerItemView) convertView;
        }

        SingerItem item = items.get(position);
        view.setbusName(item.getBusName());
        view.setfbus(item.getFbus());
        view.setNextStn(item.getNextStn());
        view.setFirstbus(item.getFirstbus());
        view.setLastbus(item.getLastbus());

        return view;
    }
}
