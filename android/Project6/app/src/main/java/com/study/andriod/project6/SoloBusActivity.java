package com.study.andriod.project6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SoloBusActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textView11;
    String key = "zwuo5Q4WhY1qySU06kW8PI1YyuxlbJvGCW0IA9gD9owovUg91hVXUnmbGt6Mu%2FM4Q8V%2F7o8PEWa3Dz1%2B2vPhTw%3D%3D";
    busTask data;
    busTask1 data1;
    boolean state = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_bus);

        Intent intent = getIntent();
        String srId = intent.getStringExtra("srId");
        String stName = intent.getStringExtra("stName");
        String busName = intent.getStringExtra("busName");

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);

        textView1.setText(stName);
        textView2.setText(srId);
        textView3.setText(busName);

        data = new busTask();
        data.execute(srId,busName);

        final Restart restart = new Restart();
        restart.setDaemon(true);
        restart.start();
    }

    public void onBtn(View v){
        Intent intent1 = getIntent();
        String srId = intent1.getStringExtra("busRouteId");
        String busRouteNm = intent1.getStringExtra("busRouteNm");
        Intent intent = new Intent(this.getApplicationContext(),bRidActivity.class);
        intent.putExtra("busRouteId",srId);
        intent.putExtra("busRouteNm",busRouteNm);
        startActivity(intent);

    }

    class Restart extends Thread{
        public void run(){
            while (state){
                mHandler.sendEmptyMessage(0);

                try{
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Intent intent = getIntent();
            String srId = intent.getStringExtra("srId");
            String busName = intent.getStringExtra("busName");
            data1 = new busTask1();
            data1.execute(srId,busName);

            mHandler.sendEmptyMessageDelayed(0,5000);
        }
    };

    private class busTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog = new ProgressDialog(SoloBusActivity.this);

        @Override
        protected String doInBackground(String...params){

            try{
                return downloadUrl(params[0],params[1]);

            }  catch (Exception e) {
                return "실패";
            }
        }

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("로딩중입니다..");

            // show dialog
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result){
            String buffer = "";
            ArrayList<String> adirection = new ArrayList<>();
            ArrayList<String> arrmsg1 = new ArrayList<>();
            ArrayList<String> arrmsg2 = new ArrayList<>();
            ArrayList<String> nxtStn = new ArrayList<>();
            ArrayList<String> stationNm1 = new ArrayList<>();
            ArrayList<String> stationNm2 = new ArrayList<>();
            ArrayList<String> lastTm = new ArrayList<>();
            ArrayList<String> firstTm = new ArrayList<>();
            ArrayList<String> rtNm = new ArrayList<>();
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();
                String tag = "";

                while (eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;

                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();

                            if(tag.equals("itemList")){
//                                SingerItem item = new SingerItem();
                            }
                            else if(tag.equals("adirection")){
                                xpp.next();
                                adirection.add(xpp.getText().toString());
                            }
                            else if(tag.equals("arrmsg1")){
                                xpp.next();
                                arrmsg1.add(xpp.getText().toString());
                            }
                            else if(tag.equals("arrmsg2")){
                                xpp.next();
                                arrmsg2.add(xpp.getText().toString());
                            }
                            else if(tag.equals("nxtStn")){
                                xpp.next();
                                nxtStn.add(xpp.getText().toString());
                            }
                            else if(tag.equals("stationNm1")){
                                xpp.next();
                                stationNm1.add(xpp.getText().toString());
                            }
                            else if(tag.equals("stationNm2")){
                                xpp.next();
                                stationNm2.add(xpp.getText().toString());
                            }
                            else if(tag.equals("firstTm")){
                                xpp.next();
                                firstTm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("lastTm")){
                                xpp.next();
                                lastTm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("rtNm")){
                                xpp.next();
                                rtNm.add(xpp.getText().toString());
                            }
                            break;
                        case XmlPullParser.TEXT:
                            break;
                        case XmlPullParser.END_TAG:
                            tag = xpp.getName();

                            if(tag.equals("itemList"))
                                break;
                    }
                    eventType = xpp.next();
                }
                Intent intent = getIntent();
                String busName = intent.getStringExtra("busName");
                dialog.dismiss();
                for(int i = 0; i < rtNm.size(); i++){
                    if(rtNm.get(i).toString().equals(busName)){
                        textView4.setText("다음 정류소 : "+nxtStn.get(i));
                        textView5.setText("방면 : "+adirection.get(i));
                        textView6.setText(arrmsg1.get(i));
                        textView7.setText("첫차 : "+firstTm.get(i)+" 막차 : "+lastTm.get(i));
                        textView8.setText("현재위치 : "+stationNm1.get(i));
                        textView9.setText(arrmsg2.get(i));
                        textView10.setText("첫차 : "+firstTm.get(i)+" 막차 : "+lastTm.get(i));
                        textView11.setText("현재위치 : "+stationNm2.get(i));
                        break;
                    }else{
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String downloadUrl(String rootnum,String busName) throws IOException {
        HttpURLConnection con = null;
        String str = rootnum;

        String location = URLEncoder.encode(str);
        String line = null;
        String page = "";

        String query = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid?serviceKey="+key+"&arsId="+location+"";
        try{
            URL url = new URL(query);
            con = (HttpURLConnection) url.openConnection();
            BufferedInputStream buf = new BufferedInputStream(con.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(buf,"utf-8"));
            while ((line = bufferedReader.readLine()) != null){
                page += line;
            }
            return page;
        }finally {
            con.disconnect();
        }

    }

    @Override
    public void onStop(){
        super.onStop();
        state = false;
        Restart.interrupted();
    }


    @Override
    public void onResume() {
        super.onResume();
        state = true;
    }
    @Override
    protected void onPause(){
        super.onPause();
        state = false;
    }

    private class busTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String...params){

            try{
                return downloadUrl(params[0],params[1]);

            }  catch (Exception e) {
                return "실패";
            }
        }

        @Override
        protected void onPostExecute(String result){
            ArrayList<String> adirection = new ArrayList<>();
            ArrayList<String> arrmsg1 = new ArrayList<>();
            ArrayList<String> arrmsg2 = new ArrayList<>();
            ArrayList<String> nxtStn = new ArrayList<>();
            ArrayList<String> stationNm1 = new ArrayList<>();
            ArrayList<String> stationNm2 = new ArrayList<>();
            ArrayList<String> lastTm = new ArrayList<>();
            ArrayList<String> firstTm = new ArrayList<>();
            ArrayList<String> rtNm = new ArrayList<>();
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();
                String tag = "";

                while (eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;

                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();

                            if(tag.equals("itemList")){
//                                SingerItem item = new SingerItem();
                            }
                            else if(tag.equals("adirection")){
                                xpp.next();
                                adirection.add(xpp.getText().toString());
                            }
                            else if(tag.equals("arrmsg1")){
                                xpp.next();
                                arrmsg1.add(xpp.getText().toString());
                            }
                            else if(tag.equals("arrmsg2")){
                                xpp.next();
                                arrmsg2.add(xpp.getText().toString());
                            }
                            else if(tag.equals("nxtStn")){
                                xpp.next();
                                nxtStn.add(xpp.getText().toString());
                            }
                            else if(tag.equals("stationNm1")){
                                xpp.next();
                                stationNm1.add(xpp.getText().toString());
                            }
                            else if(tag.equals("stationNm2")){
                                xpp.next();
                                stationNm2.add(xpp.getText().toString());
                            }
                            else if(tag.equals("firstTm")){
                                xpp.next();
                                firstTm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("lastTm")){
                                xpp.next();
                                lastTm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("rtNm")){
                                xpp.next();
                                rtNm.add(xpp.getText().toString());
                            }
                            break;
                        case XmlPullParser.TEXT:
                            break;
                        case XmlPullParser.END_TAG:
                            tag = xpp.getName();

                            if(tag.equals("itemList"))
                                break;
                    }
                    eventType = xpp.next();
                }
                Intent intent = getIntent();
                String busName = intent.getStringExtra("busName");
                for(int i = 0; i < rtNm.size(); i++){
                    if(rtNm.get(i).toString().equals(busName)){
                        textView4.setText("다음 정류소 : "+nxtStn.get(i));
                        textView5.setText("방면 : "+adirection.get(i));
                        textView6.setText(arrmsg1.get(i));
                        textView7.setText("첫차 : "+firstTm.get(i)+" 막차 : "+lastTm.get(i));
                        textView8.setText("현재위치 : "+stationNm1.get(i));
                        textView9.setText(arrmsg2.get(i));
                        textView10.setText("첫차 : "+firstTm.get(i)+" 막차 : "+lastTm.get(i));
                        textView11.setText("현재위치 : "+stationNm2.get(i));
                        break;
                    }else{
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
