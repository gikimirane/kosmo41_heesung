package com.study.andriod.customviewex;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtn1Clicked(View v){
        final Dialog loginDialog = new Dialog(this);
        loginDialog.setContentView(R.layout.custom_dialog);
        loginDialog.setTitle("login View");

        final EditText username = loginDialog.findViewById(R.id.editText1);
        final EditText password = loginDialog.findViewById(R.id.editText2);

        Button login = loginDialog.findViewById(R.id.button1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().length() > 0 &&
                        password.getText().toString().trim().length() > 0){
                    Toast.makeText(getApplicationContext(),"로그인 성공", Toast.LENGTH_LONG).show();

                    loginDialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(),"다시 입력하세요.",Toast.LENGTH_LONG).show();
                }
            }
        });

        Button cancel = loginDialog.findViewById(R.id.button2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog.dismiss();;
            }
        });

        loginDialog.show();

    }
}
