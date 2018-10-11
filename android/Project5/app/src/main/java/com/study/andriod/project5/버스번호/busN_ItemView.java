package com.study.andriod.project5.버스번호;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.andriod.project5.R;

public class busN_ItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;


    public busN_ItemView(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.busnum_item_view,this,true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

    }

    public void setbusRouteNm(String busRouteNm){
        textView1.setText(busRouteNm);
    }

    public void setedStationNm(String edStationNm){
        textView3.setText("종점 : "+edStationNm);
    }

    public void setstStationNm(String stStationNm){
        textView2.setText("기점 : "+stStationNm);
    }

}
