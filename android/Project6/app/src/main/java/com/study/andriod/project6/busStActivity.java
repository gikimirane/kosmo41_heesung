package com.study.andriod.project6;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.andriod.project6.정류소.St_Adapter;
import com.study.andriod.project6.정류소.St_Item;

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

public class busStActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    String key = "zwuo5Q4WhY1qySU06kW8PI1YyuxlbJvGCW0IA9gD9owovUg91hVXUnmbGt6Mu%2FM4Q8V%2F7o8PEWa3Dz1%2B2vPhTw%3D%3D";
    St_Adapter adapter;
    busTask data;
    ListView listView;
    TextView textView1;
    TextView textView2;
    ImageButton button;
    DBHelper mydb;
    boolean state = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_st);

        adapter = new St_Adapter(this);
        Intent intent = getIntent();
        final String srId = intent.getStringExtra("srId");
        final String stName = intent.getStringExtra("stName");


        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.imageButton2);

        textView1.setText(stName);
        textView2.setText(srId);

        data = new busTask();
        data.execute(srId);


        listView = findViewById(R.id.listView6);
        listView.setAdapter(adapter);

        Restart restart = new Restart();
        restart.setDaemon(true);
        restart.start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                St_Item item = (St_Item) adapter.getItem(position);
                //이게 다른액티비티로 값보내주는거. 인텐트로 받으면됨 그쪽에서도. 액티비티 보낼곳 이름 바꾸기 와서.
                Intent intent = new Intent(getApplicationContext(),SoloBusActivity.class);
                intent.putExtra("srId",srId);
                intent.putExtra("stName",stName);
                intent.putExtra("busName",item.getBusName());
                intent.putExtra("busRouteId",item.getBusRouteId());
                intent.putExtra("busRouteNm",item.getBusName());
                startActivityForResult(intent,1);

            }
        });
    }
    public void onBtn(View v){
        try{
            String stnum = textView2.getText().toString();
            String stname = textView1.getText().toString();
            mydb = new DBHelper(this);
            mydb.insertData(stname,stnum);
            Toast.makeText(getApplicationContext(),"즐겨찾기 추가되었습니다.",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class Restart extends Thread{
        public void run(){
            while (state){
                mHandler.sendEmptyMessage(0);

                try{
                    Log.d(TAG,"실행중");
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
            final String srId = intent.getStringExtra("srId");
            data = new busTask();
            data.execute(srId);
        }
    };

    private class busTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String...params){

            try{
                return (String)downloadUrl((String) params[0]);

            }  catch (Exception e) {
                return "실패";
            }
        }

        @Override
        protected void onPostExecute(String result){
            String buffer = "";
            ArrayList<String> rtNm = new ArrayList<>();
            ArrayList<String> arrmsg1 = new ArrayList<>();
            ArrayList<String> nxtStn = new ArrayList<>();
            ArrayList<String> firstTm = new ArrayList<>();
            ArrayList<String> lastTm = new ArrayList<>();
            ArrayList<String> busRouteId = new ArrayList<>();
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
                            else if(tag.equals("rtNm")){
                                xpp.next();
                                rtNm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("arrmsg1")){
                                xpp.next();
                                arrmsg1.add(xpp.getText().toString());
                            }
                            else if(tag.equals("nxtStn")){
                                xpp.next();
                                nxtStn.add(xpp.getText().toString());
                            }
                            else if(tag.equals("firstTm")){
                                xpp.next();
                                firstTm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("lastTm")){
                                xpp.next();
                                lastTm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("busRouteId")){
                                xpp.next();
                                busRouteId.add(xpp.getText().toString());
                            }
                            else if(tag.equals("stNm")){
                                xpp.next();
                                buffer = xpp.getText().toString();
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
                adapter.clearItem();
                for(int i = 0; i < lastTm.size(); i++){
                    St_Item item = new St_Item(rtNm.get(i),arrmsg1.get(i),nxtStn.get(i),lastTm.get(i),firstTm.get(i),busRouteId.get(i));
                    adapter.addItem(item);
                }
                rtNm.clear();
                arrmsg1.clear();
                nxtStn.clear();
                lastTm.clear();
                firstTm.clear();
                busRouteId.clear();
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String downloadUrl(String rootnum) throws IOException {
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
}
