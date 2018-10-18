package com.study.andriod.project6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.study.andriod.project6.노선.bRid_Adapter;
import com.study.andriod.project6.노선.bRid_Item;

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

public class bRidActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    String key = "zwuo5Q4WhY1qySU06kW8PI1YyuxlbJvGCW0IA9gD9owovUg91hVXUnmbGt6Mu%2FM4Q8V%2F7o8PEWa3Dz1%2B2vPhTw%3D%3D";
    busTask data;
    bRid_Adapter adapter;

    sectOrd data1;
    ArrayList<String> arsId = new ArrayList<>();
    ArrayList<String> beginTm = new ArrayList<>();
    ArrayList<String> lastTm = new ArrayList<>();
    ArrayList<String> stationNm = new ArrayList<>();
    ArrayList<String> seq = new ArrayList<>();
    ArrayList<String> sectOrd = new ArrayList<>();
    TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_rid);

        adapter = new bRid_Adapter(this);
        textView = findViewById(R.id.textView55);
        Intent intent = getIntent();
        String busRouteId = intent.getStringExtra("busRouteId");
        String busRouteNm = intent.getStringExtra("busRouteNm");
        textView.setText(busRouteNm);
        data1 = new sectOrd();
        data1.execute(busRouteId);

        listView = findViewById(R.id.listView5);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                bRid_Item item = (bRid_Item) adapter.getItem(position);
                //이게 다른액티비티로 값보내주는거. 인텐트로 받으면됨 그쪽에서도. 액티비티 보낼곳 이름 바꾸기 와서.
                Intent intent = new Intent(getApplicationContext(),busStActivity.class);
                intent.putExtra("srId",item.getArsId());
                intent.putExtra("stName",item.getStationNm());
                startActivityForResult(intent,1);

            }
        });
    }

    private class busTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog = new ProgressDialog(bRidActivity.this);

        @Override
        protected String doInBackground(String...params){

            try{
                return (String)downloadUrl((String) params[0]);

            }  catch (Exception e) {
                return "실패";
            }
        }

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
                            else if(tag.equals("arsId")){
                                xpp.next();
                                arsId.add(xpp.getText().toString());
                            }
                            else if(tag.equals("beginTm")){
                                xpp.next();
                                beginTm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("lastTm")){
                                xpp.next();
                                lastTm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("seq")){
                                xpp.next();
                                seq.add(xpp.getText().toString());
                            }
                            else if(tag.equals("stationNm")){
                                xpp.next();
                                stationNm.add(xpp.getText().toString());
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
                int a = 0;
                for(int i = 0; i < stationNm.size(); i++){
                    a = 0;
                    for(int j = 0; j < sectOrd.size(); j++){
                        if(seq.get(i).toString().equals(sectOrd.get(j).toString())) {
                            Log.d(TAG,"ss1a : "+seq.get(i).toString()+"   :  "+sectOrd.get(j).toString());
                            bRid_Item item = new bRid_Item(arsId.get(i), beginTm.get(i), lastTm.get(i), stationNm.get(i), seq.get(i), "a");
                            adapter.addItem(item);
                            a++;
                            break;
                        }
                    }
                    if(a==0) {
                        bRid_Item item = new bRid_Item(arsId.get(i), beginTm.get(i), lastTm.get(i), stationNm.get(i), seq.get(i), "b");
                        adapter.addItem(item);
                    }
                }
                arsId.clear();
                beginTm.clear();
                lastTm.clear();
                stationNm.clear();
                seq.clear();
                dialog.dismiss();
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

        String query = "http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute?serviceKey="+key+"&busRouteId="+location+"";
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

    private class sectOrd extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String...params){

            try{
                return (String)downloadUrl1((String) params[0]);

            }  catch (Exception e) {
                return "실패";
            }
        }

        @Override
        protected void onPostExecute(String result){
            String buffer = "";
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
                            else if(tag.equals("sectOrd")){
                                xpp.next();
                                sectOrd.add(xpp.getText().toString());
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
                String busRouteId = intent.getStringExtra("busRouteId");
                data = new busTask();
                data.execute(busRouteId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String downloadUrl1(String rootnum) throws IOException {
        HttpURLConnection con = null;
        String str = rootnum;

        String location = URLEncoder.encode(str);
        String line = null;
        String page = "";

        String query = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?serviceKey="+key+"&busRouteId="+location+"";
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
}
