package com.study.andriod.project6.노선;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.andriod.project6.R;

public class bRid_ItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    ImageView imageView;


    public bRid_ItemView(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.brid_item_view,this,true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        imageView = findViewById(R.id.imageView4);
    }

    public void setstationNm(String stationNm){
        textView1.setText(stationNm);
    }

    public void setarsId(String arsId){
        textView2.setText(arsId+" / ");
    }

    public void setbeginTm(String beginTm){
        textView3.setText("운행시간 : "+beginTm+" ~ ");
    }

    public void setlastTm(String lastTm){
        textView4.setText(lastTm);
    }

    public void setImageView(String a){
        if(a.equals("a")) {
            imageView.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.INVISIBLE);
        }
    }


}
