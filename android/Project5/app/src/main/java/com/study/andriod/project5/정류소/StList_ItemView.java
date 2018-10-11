package com.study.andriod.project5.정류소;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.andriod.project5.R;

public class StList_ItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    ImageView imageView;


    public StList_ItemView(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stlist_item_view,this,true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

    }

    public void setBusName(String busName){
        textView2.setText(busName);
    }

    public void setStNum(String stNum){
        textView1.setText(stNum);
    }


}
