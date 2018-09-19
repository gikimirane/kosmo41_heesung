package com.study.andriod.animation;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    ImageView imageView1;
    Animation anim1;
    Animation anim2;
    Animation anim3;

    int i = 0;
    float x = 0.0f;
    float y = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView);

        anim1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.size_change);
        anim3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.change_alpha);
    }

    public void onBtn1Click(View v){
        imageView1.startAnimation(anim1);
    }
    public void onBtn2Click(View v){
        imageView1.startAnimation(anim2);
    }
    public void onBtn3Click(View v){
        imageView1.startAnimation(anim3);
    }

    //이런 기능도 있다.
    public void onBtn4Click(View v){
        i = 0;
        x = 0.0f;
        y = 0.0f;
        mHandler.sendEmptyMessage(0);
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg){
            Matrix matrix = new Matrix();
            //matrix.postRotate(45.0f);
            // matrix.postScale(2.0f,2.0f);
            x = x + 10.0f;
            y = y + 10.0f;
            matrix.postTranslate(x,y);

            imageView1.setImageMatrix(matrix);

            Log.d(TAG,"Timer:" +i);
            if(i<20) {
                mHandler.sendEmptyMessageDelayed(0,500);
            }
            i++;
        }

    };
}
