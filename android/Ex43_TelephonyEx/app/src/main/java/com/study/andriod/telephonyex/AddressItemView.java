package com.study.andriod.telephonyex;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddressItemView extends LinearLayout {

    TextView textView1;
    Button button1;
    ImageView imageView1;

    public AddressItemView(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.address_item_view,this,true);

        textView1 = findViewById(R.id.txtName);
        button1 = findViewById(R.id.btnTelNum);
        imageView1 = findViewById(R.id.imageView1);
    }

    public void setName(String name){
        textView1.setText(name);
    }

    public void setTel(String Telnum){
        button1.setText(Telnum);
    }

    public void setImage(Bitmap imgNum){
        imageView1.setImageBitmap(imgNum);
    }



}
