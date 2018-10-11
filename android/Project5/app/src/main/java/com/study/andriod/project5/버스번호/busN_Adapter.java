package com.study.andriod.project5.버스번호;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class busN_Adapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<busN_Item> items = new ArrayList<>();


    public busN_Adapter(Context context){
        this.context = context;
    }

    public void addItem(busN_Item item){ items.add(item);}

    public void clearItem(){ items.clear();}

    @Override
    public int getCount(){return  items.size();}

    @Override
    public Object getItem(int position){ return items.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){



        busN_ItemView view = null;
        if(convertView == null){
            view = new busN_ItemView(context);
        }else {
            view = (busN_ItemView) convertView;
        }

        busN_Item item = items.get(position);
        view.setbusRouteNm(item.getBusRouteNm());
        view.setstStationNm(item.getStStationNm());
        view.setedStationNm(item.getEdStationNm());


        return view;
    }
}
