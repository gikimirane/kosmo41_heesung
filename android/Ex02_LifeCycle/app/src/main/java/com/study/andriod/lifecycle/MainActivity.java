package com.study.andriod.lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        Log.d(TAG,"콜백 함수 호출됨");

        if(requestCode == 1 && resultCode == 10){
            String sData = "";
            String str = "OnActivityResult() called : "+
                    requestCode + " : " +
                    resultCode;

            sData = data.getStringExtra("BackData");
            str = str + " : "+sData;

            Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClicked(View v){
        Intent intent = new Intent(getApplicationContext(),NewActivity.class);
        startActivityForResult(intent,1);
    }

}
