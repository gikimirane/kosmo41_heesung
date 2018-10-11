package com.study.andriod.project6.즐겨찾기;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.andriod.project6.R;

public class Fr1_ItemView extends LinearLayout {
    private static final String TAG = "lecture";

    TextView textView1;
    TextView textView2;


    public Fr1_ItemView(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.fr1_singer_item_view,this,true);

        textView1 = findViewById(R.id.textView11);
        textView2 = findViewById(R.id.textView22);
    }

    public void setStName(String stName){
        textView2.setText(stName);
    }

    public void setStNum(String stNum){
        textView1.setText(stNum);
    }

}
