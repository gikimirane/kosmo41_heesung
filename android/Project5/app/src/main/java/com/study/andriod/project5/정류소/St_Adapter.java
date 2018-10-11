package com.study.andriod.project5.정류소;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class St_Adapter extends BaseAdapter {

    Context context;
    ArrayList<St_Item> items = new ArrayList<>();


    public St_Adapter(Context context){
        this.context = context;
    }

    public void addItem(St_Item item){ items.add(item);}

    public void clearItem(){ items.clear();}

    @Override
    public int getCount(){return  items.size();}

    @Override
    public Object getItem(int position){ return items.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){



        St_ItemView view = null;
        if(convertView == null){
            view = new St_ItemView(context);
        }else {
            view = (St_ItemView) convertView;
        }

        St_Item item = items.get(position);
        view.setbusName(item.getBusName());
        view.setfbus(item.getFbus());
        view.setNextStn(item.getNextStn());
        view.setFirstbus(item.getFirstbus());
        view.setLastbus(item.getLastbus());

        return view;
    }
}
