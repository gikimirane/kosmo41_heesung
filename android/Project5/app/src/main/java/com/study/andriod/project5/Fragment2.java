package com.study.andriod.project5;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.study.andriod.project5.정류소.StList_Adapter;
import com.study.andriod.project5.정류소.StList_Item;
import com.study.andriod.project5.정류소.St_Adapter;
import com.study.andriod.project5.정류소.St_Item;

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
    DBHelper mydb;
    EditText edit;
    String key = "zwuo5Q4WhY1qySU06kW8PI1YyuxlbJvGCW0IA9gD9owovUg91hVXUnmbGt6Mu%2FM4Q8V%2F7o8PEWa3Dz1%2B2vPhTw%3D%3D";
    StList_Adapter stList_adapter;
    busTask data;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fragment2,container,false);

        edit = rootView.findViewById(R.id.editText);

        stList_adapter = new StList_Adapter(getActivity());
        mydb = new DBHelper(getActivity());



        Button button = rootView.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = new busTask();
                data.execute(edit.getText().toString());
                InputMethodManager immhide = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });


        listView = rootView.findViewById(R.id.listView);
        Log.d(TAG,"111111");
        listView.setAdapter(stList_adapter);
        Log.d(TAG,"2222222");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                StList_Item item = (StList_Item) stList_adapter.getItem(position);
                //이게 다른액티비티로 값보내주는거. 인텐트로 받으면됨 그쪽에서도. 액티비티 보낼곳 이름 바꾸기 와서.
                Intent intent = new Intent(getActivity().getApplicationContext(),busStActivity.class);
                intent.putExtra("srId",item.getBusRouteId());
                intent.putExtra("stName",item.getStNum());
                Log.d(TAG,item.getBusRouteId()+"1:2q"+item.getStNum());
                startActivityForResult(intent,1);

            }
        });

        return rootView;
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
            String rtNm = "";
            String arsId = "";
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
                                rtNm = xpp.getText().toString();
                            }
                            else if(tag.equals("arsId")){
                                xpp.next();
                                arsId = xpp.getText().toString();
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
                stList_adapter.clearItem();
                StList_Item item = new StList_Item(rtNm,arsId,buffer.toString());
                stList_adapter.addItem(item);
                stList_adapter.notifyDataSetChanged();
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
