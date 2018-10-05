package com.study.andriod.project5;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


public class Fragment2 extends Fragment {
    private static final String TAG = "lecture";
    EditText edit;
    TextView textView;
    String key = "zwuo5Q4WhY1qySU06kW8PI1YyuxlbJvGCW0IA9gD9owovUg91hVXUnmbGt6Mu%2FM4Q8V%2F7o8PEWa3Dz1%2B2vPhTw%3D%3D";
    St_Adapter adapter;
    busTask data;
    ListView listView;
    Context ct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fragment2,container,false);
        ct = container.getContext();

        edit = rootView.findViewById(R.id.editText);
        textView = rootView.findViewById(R.id.textView);

        adapter = new St_Adapter(ct);

        Button button = rootView.findViewById(R.id.button2);
        Button button1 = rootView.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = new busTask();
                data.execute(edit.getText().toString());
                InputMethodManager immhide = (InputMethodManager) ct.getSystemService(Context.INPUT_METHOD_SERVICE);
                immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = new busTask();
                data.execute(edit.getText().toString());
            }
        });

        listView = rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                St_Item item = (St_Item) adapter.getItem(position);
                Toast.makeText(rootView.getContext(),
                        "selected : "+item.getBusName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    public void onBtn(View view) {
        data = new busTask();
        data.execute(edit.getText().toString());

    }


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
                    St_Item item = new St_Item(rtNm.get(i),arrmsg1.get(i),nxtStn.get(i),lastTm.get(i),firstTm.get(i));
                    adapter.addItem(item);
                }
                rtNm.clear();
                arrmsg1.clear();
                nxtStn.clear();
                lastTm.clear();
                firstTm.clear();
                textView.setText(buffer.toString());
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

}
