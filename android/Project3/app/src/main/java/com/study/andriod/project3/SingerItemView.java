package com.study.andriod.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;


    public SingerItemView(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item_view,this,true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
    }

    public void setbusName(String busName){
        textView1.setText(busName);
    }

    public void setNextStn(String nextStn){
        textView2.setText(nextStn);
    }

    public void setfbus(String fbus){
        textView3.setText(fbus);
    }

    public void setFirstbus(String Firstbus){
        textView4.setText("첫차 :"+Firstbus);
    }

    public void setLastbus(String Lastbus){
        textView5.setText("막차 :"+Lastbus);
    }

//    public TextView getTextView1() {
//        return textView1;
//    }
//
//    public void setTextView1(TextView textView1) {
//        this.textView1 = textView1;
//    }
//
//    public TextView getTextView2() {
//        return textView2;
//    }
//
//    public void setTextView2(TextView textView2) {
//        this.textView2 = textView2;
//    }
//
//    public TextView getTextView3() {
//        return textView3;
//    }
//
//    public void setTextView3(TextView textView3) {
//        this.textView3 = textView3;
//    }
//
//    public TextView getTextView4() {
//        return textView4;
//    }
//
//    public void setTextView4(TextView textView4) {
//        this.textView4 = textView4;
//    }
//
//    public TextView getTextView5() {
//        return textView5;
//    }
//
//    public void setTextView5(TextView textView5) {
//        this.textView5 = textView5;
//    }
}
