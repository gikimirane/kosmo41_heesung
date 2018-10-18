package com.study.andriod.project6;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import com.study.andriod.project6.버스번호.busN_Adapter;
import com.study.andriod.project6.버스번호.busN_Item;

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


public class Fragment3 extends Fragment {
    private static final String TAG = "lecture";
    EditText edit;
    String key = "zwuo5Q4WhY1qySU06kW8PI1YyuxlbJvGCW0IA9gD9owovUg91hVXUnmbGt6Mu%2FM4Q8V%2F7o8PEWa3Dz1%2B2vPhTw%3D%3D";
    busTask data;
    busN_Adapter adapter;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fragment3,container,false);

        edit = rootView.findViewById(R.id.editText2);
        adapter = new busN_Adapter(getActivity());

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = new busTask();
                data.execute(edit.getText().toString());
                InputMethodManager immhide = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });

        listView = rootView.findViewById(R.id.listView4);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                busN_Item item = (busN_Item) adapter.getItem(position);
                Intent intent = new Intent(getActivity().getApplicationContext(),bRidActivity.class);
                intent.putExtra("busRouteId",item.getBusRouteId());
                intent.putExtra("busRouteNm",item.getBusRouteNm());
                Log.d(TAG,item.getBusRouteId());
                startActivityForResult(intent,1);
            }
        });

        return rootView;
    }

    private class busTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog = new ProgressDialog(getActivity());

        @Override
        protected String doInBackground(String...params){

            try{
                return (String)downloadUrl((String) params[0]);

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
            ArrayList<String> busRouteId = new ArrayList<>();
            ArrayList<String> busRouteNm = new ArrayList<>();
            ArrayList<String> edStationNm = new ArrayList<>();
            ArrayList<String> stStationNm = new ArrayList<>();
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
                            else if(tag.equals("busRouteId")){
                                xpp.next();
                                busRouteId.add(xpp.getText().toString());
                            }
                            else if(tag.equals("busRouteNm")){
                                xpp.next();
                                busRouteNm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("edStationNm")){
                                xpp.next();
                                edStationNm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("stStationNm")){
                                xpp.next();
                                stStationNm.add(xpp.getText().toString());
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
                for(int i = 0; i < stStationNm.size(); i++){
                    busN_Item item = new busN_Item(busRouteId.get(i),busRouteNm.get(i),edStationNm.get(i),stStationNm.get(i));

                    adapter.addItem(item);
                }
                busRouteId.clear();
                busRouteNm.clear();
                edStationNm.clear();
                stStationNm.clear();
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

        String query = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?serviceKey="+key+"&strSrch="+location+"";
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
