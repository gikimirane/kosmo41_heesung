package com.study.andriod.edittext2;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView textView;

    EditText etId;
    EditText etPwd;
    EditText etYear;
    EditText etMonth;
    EditText etDay;

    String sId;
    String sPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        etId = findViewById(R.id.etId);
        etPwd = findViewById(R.id.etPwd);
        etYear = findViewById(R.id.etYear);
        etMonth = findViewById(R.id.etMonth);
        etDay = findViewById(R.id.etDay);

        //패스워드 입력시 글자수 체크
        etPwd.addTextChangedListener(watcher);
    }
        //inputType 과 maxLength가 editText에 지정되어 있으면 키보드에 [리턴]이 [다음]으로 됨
        // 맨 마지막 입력칸은 자동으로 [완료]가 됨
        //입력칸이 키보드에 가려지면 자동으로 올라감

        //키보드 내리기 버튼
        public void onKeydownClicked(View v){
            //IME Hide
            InputMethodManager mgr =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(textView.getWindowToken(),0);
        }

        public void onLoginClicked(View v){
            sId = etId.getText().toString();
            sPwd = etPwd.getText().toString();

            if(sId.length() < 3){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("알림")
                        .setMessage("아이디를 입력해 주세요")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("닫기",null)
                        .show();
                etId.requestFocus();
                return;
            }else if (sPwd.length() < 5){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("알림")
                        .setMessage("비밀번호를 정확히 입력해 주세요")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("닫기",null)
                        .show();
                etPwd.requestFocus();
                return;
            }
        }

        TextWatcher watcher = new TextWatcher() {

            String beforeText;
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {
                beforeText = str.toString();
                Log.d(TAG, "beforeTextChanged : "+beforeText);
            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {
                byte[] bytes = null;
                try{

                    //KSC 가 2바이트로 8859가 1글자로 길이 리턴 8859_1 유니코드임
                    //bytes = str.toString().getBytes("KSC5601");
                    bytes = str.toString().getBytes("8859_1");
                    int strCount = bytes.length;
                    textView.setText(strCount + "/ 8 바이트");
                }catch (UnsupportedEncodingException ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable strEditable) {
                String str = strEditable.toString();
                Log.d(TAG, "afterTextChanged : "+ str);
                try{
                    byte[] strbytes = str.getBytes("8859_1");
                    if(strbytes.length > 8){
                        etPwd.setText(beforeText);
                        etPwd.setSelection(beforeText.length()-1, beforeText.length()-1);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };

}
