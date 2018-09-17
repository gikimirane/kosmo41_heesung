package com.study.andriod.Hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //버튼 1:
        //클릭 이벤트 추가
        //로그 출력 추가
        // 토스트 생성 추가
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.d(TAG,"로그출력");
                Toast.makeText(getApplicationContext(),"긴 토스트",Toast.LENGTH_LONG).show();
            }
        });
    }


    //버튼 2 :
    // 인텐트 만들어 웹브라우저 띄우기
    public void onBtn2Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);

    }

    public void onBtn3Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01097602240"));
        startActivity(intent);

    }



}
