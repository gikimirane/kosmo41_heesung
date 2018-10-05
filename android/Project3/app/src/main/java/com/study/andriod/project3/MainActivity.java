package com.study.andriod.project3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.study.andriod.project3.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    EditText edit;
    TextView textView;
    String key = "zwuo5Q4WhY1qySU06kW8PI1YyuxlbJvGCW0IA9gD9owovUg91hVXUnmbGt6Mu%2FM4Q8V%2F7o8PEWa3Dz1%2B2vPhTw%3D%3D";
    SingerAdapter adapter;
    busTask data;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},1);
        }
        adapter = new SingerAdapter(this);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),
                        "selected : "+item.getBusName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBtn(View v) {
        data = new busTask();
        data.execute(edit.getText().toString());
    }

    private class busTask extends AsyncTask<String, Void, String>{

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
            StringBuffer buffer = new StringBuffer();
            ArrayList<String> rtNm = new ArrayList<>();
            ArrayList<String> arrmsg1 = new ArrayList<>();
            ArrayList<String> nxtStn = new ArrayList<>();
            ArrayList<String> firstTm = new ArrayList<>();
            ArrayList<String> lastTm = new ArrayList<>();
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
                                buffer.append("버스이름 : ");
                                xpp.next();
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                                rtNm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("arrmsg1")){
                                buffer.append("정류소 도착 : ");
                                xpp.next();
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                                arrmsg1.add(xpp.getText().toString());
                            }
                            else if(tag.equals("nxtStn")){
                                buffer.append("다음 정거장 : ");
                                xpp.next();
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                                nxtStn.add(xpp.getText().toString());
                            }
                            else if(tag.equals("firstTm")){
                                buffer.append("첫차 : ");
                                xpp.next();
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                                firstTm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("lastTm")){
                                buffer.append("막차 : ");
                                xpp.next();
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                                lastTm.add(xpp.getText().toString());
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
                    SingerItem item = new SingerItem(rtNm.get(i),arrmsg1.get(i),nxtStn.get(i),lastTm.get(i),firstTm.get(i));
                    adapter.addItem(item);
                    Log.d(TAG,lastTm.get(i)+"1212");
                }
                rtNm.clear();
                arrmsg1.clear();
                nxtStn.clear();
                lastTm.clear();
                firstTm.clear();
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String downloadUrl(String rootnum) throws IOException{
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
}
