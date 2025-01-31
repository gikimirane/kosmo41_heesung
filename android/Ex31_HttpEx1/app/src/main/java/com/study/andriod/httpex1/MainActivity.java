package com.study.andriod.httpex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    Button btnGetAct;
    Button btnPostAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetAct = findViewById(R.id.btnGetAct);
        btnPostAct = findViewById(R.id.btnPostAct);
    }

    public void onBtnGet(View v){
        Intent intent = new Intent(MainActivity.this, HttpGetActivity.class);
        startActivity(intent);
    }
    public void onBtnPost(View v){
        Intent intent = new Intent(MainActivity.this, HttpPostActivity.class);
        startActivity(intent);
    }
}
