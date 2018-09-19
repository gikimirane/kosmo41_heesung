package com.study.andriod.homework1;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class woman extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    ImageView imageView2;

    public woman(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.woman,this,true);

        textView1 = findViewById(R.id.textView4);
        textView2 = findViewById(R.id.textView5);
        textView3 = findViewById(R.id.textView6);
        imageView2 = findViewById(R.id.imageView2);
    }

    public void setName(String name){
        textView1.setText(name);
    }

    public void setAge(String age){
        textView2.setText(age);
    }

    public void setHoom(String hoom){
        textView3.setText(hoom);
    }

    public void setHuman(String human){

    }

    public void setImage(int imgNum){
        imageView2.setImageResource(imgNum);
    }

}
