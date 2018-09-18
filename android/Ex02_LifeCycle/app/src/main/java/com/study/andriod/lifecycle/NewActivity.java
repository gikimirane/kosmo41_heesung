package com.study.andriod.lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Toast.makeText(getApplicationContext(),"onCreate() 호출됨",Toast.LENGTH_SHORT).show();
    }

    public void onBtn2Clicked(View v){
        //현재 인텐트 종료시 인텐트에 전달할 데이터 세팅
        Intent intent = new Intent();
        intent.putExtra("BackData","강감찬");
        setResult(10,intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart() 호출됨",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"onResume() 호출됨",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"onPause() 호출됨",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"onStop() 호출됨",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestroy() 호출됨",Toast.LENGTH_SHORT).show();
    }

}
