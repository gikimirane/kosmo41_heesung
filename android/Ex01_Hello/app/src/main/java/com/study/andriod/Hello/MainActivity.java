package com.study.andriod.Hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    EditText editText;
    TextView textView;

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

    //버튼3
    //인텐트 만들어 전화 띄우기
    public void onBtn3Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01097602240"));
        startActivity(intent);

    }

    //버튼 4 에딧텍스트 값을 밑에 텍스트 뷰로 넣어주는기능
    public void onBtn4Clicked(View v){
        editText = findViewById(R.id.editText1);
        textView = findViewById(R.id.textView1);

        textView.setText(editText.getText());
    }
    //버튼 5 새로운 액티비티 열기.
    public void onBtn5Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),NewActivity.class);
        //풋엑스트라로 값을 넘겨줄수도있다.
        intent.putExtra("CustomName","장희성");
        startActivityForResult(intent,1);
    }

//    //버튼 5 새로운 액티비티 열기.
//    public void onBtn5Clicked(View v){
//        Intent intent = new Intent(getApplicationContext(),NewActivity.class);
//        //풋엑스트라로 값을 넘겨줄수도있다.
//        intent.putExtra("CustomName","장희성");
//        startActivity(intent);
//    }



}
