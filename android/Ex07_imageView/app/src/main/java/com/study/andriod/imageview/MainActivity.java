package com.study.andriod.imageview;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Scroller;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    ScrollView scrollerView01;
    ImageView imageView01;
    ImageView imageView02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView01 = findViewById(R.id.imageView01);
        imageView02 = findViewById(R.id.imageView02);

        imageView01.setImageResource(R.drawable.background1);
        imageView02.setImageResource(0);

        imageView01.invalidate();
        imageView02.invalidate();

        scrollerView01 = findViewById(R.id.scrollView01);
        scrollerView01.setVerticalScrollBarEnabled(true);
        scrollerView01.setHorizontalScrollBarEnabled(true);
    }

    public void onBtn1Clicked(View v){ imageDown();}
    public void onBtn2Clicked(View v){ imageUp();}

    private void imageDown(){
        imageView01.setImageResource(0);
        imageView02.setImageResource(R.drawable.background1);

        imageView01.invalidate();
        imageView02.invalidate();
    }

    private void imageUp(){
        imageView01.setImageResource(R.drawable.background1);
        imageView02.setImageResource(0);

        imageView01.invalidate();
        imageView02.invalidate();
    }
}
