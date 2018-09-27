package com.study.andriod.thread1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView textView;
    Button button;
    ProgressHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new ProgressHandler();

        textView = findViewById(R.id.textView1);
        button = findViewById(R.id.button1);

    }

    public void onBtn1(View v){
        RequestThread thread = new RequestThread();
        thread.start();
    }

    class RequestThread extends Thread {
        public void run(){
            for(int i = 0; i<20; i++){
                Log.d(TAG,"Request Thread .."+i);

//                //1 : 쓰레드에서 메인 쓰레드의 객체로 접근은 불가능
//                textView.setText("Request Thread .."+i);
                Message msg = handler.obtainMessage();

                Bundle bundle = new Bundle();
                bundle.putString("data1","Request Thread.."+i);
                bundle.putString("data2",String.valueOf(i));
                msg.setData(bundle);

                handler.sendMessage(msg);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class ProgressHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            Bundle bundle = msg.getData();
            String data1 = bundle.getString("data1");
            String data2 = bundle.getString("data2");

            textView.setText(data1);

            if(data2.equals("19")){
                textView.setText("쓰레드 테스트");
                button.setEnabled(true);
            }else{
                button.setEnabled(false);
            }
        }
    }
}
