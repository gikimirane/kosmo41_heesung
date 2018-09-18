package com.study.andriod.Hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    String sName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Intent intent = getIntent();
        sName = intent.getStringExtra("CustomName");
    }

    public void onBtn1Clicked(View v){
        Toast.makeText(getApplicationContext(),"CustomName :"+sName, Toast.LENGTH_SHORT).show();

    }
    public void onBtn2Clicked(View v){
        //현재 인텐트 종료시 인텐트에 전달할 데이터 세팅
        Intent intent = new Intent();
        intent.putExtra("BackData","강감찬");
        setResult(10,intent);
        finish();
    }
}
