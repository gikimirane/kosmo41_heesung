package com.study.andriod.project5;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.study.andriod.project5.정류소.St_Adapter;

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


public class Fragment4 extends Fragment {
    private static final String TAG = "lecture";
    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions myLocationMarker;
    TextView textView;
    String key = "zwuo5Q4WhY1qySU06kW8PI1YyuxlbJvGCW0IA9gD9owovUg91hVXUnmbGt6Mu%2FM4Q8V%2F7o8PEWa3Dz1%2B2vPhTw%3D%3D";
    St_Adapter adapter;
    busTask data;
    //정류소 번호
    ArrayList<String> arsId = new ArrayList<>();
    //정류소 이름
    ArrayList<String> stationNm = new ArrayList<>();
    ArrayList<String> posX = new ArrayList<>();
    ArrayList<String> posY = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fragment4,container,false);
        if(ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG,"GoogleMap is ready");
                map = googleMap;

                requestMyLocation();

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getActivity().getApplicationContext(),marker.getSnippet()+": 클릭함",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Intent intent = new Intent(getActivity().getApplicationContext(),busStActivity.class);
                        intent.putExtra("srId",marker.getSnippet());
                        Log.d(TAG,marker.getSnippet());
                        intent.putExtra("stName",marker.getTitle());
                        startActivityForResult(intent,1);
                    }
                });
            }
        });
        try {
            MapsInitializer.initialize(getActivity());
        } catch (Exception e){
            e.printStackTrace();
        }

        return rootView;
    }


    private void requestMyLocation() {
        LocationManager manager =
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        try {
            long minTime = 10000;
            float minDistance = 0;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );

            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation != null){
                showCurrentLocation(lastLocation);
            }

            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);

                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );

        } catch (SecurityException e){
            e.printStackTrace();
        }
    }

    private  void showCurrentLocation(Location location){
        LatLng curPoint = new LatLng(location.getLatitude(),location.getLongitude());

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));
        //이거 없애면 애니메이션없이 바로 이동됨
        String X = String.valueOf(location.getLatitude());
        String Y = String.valueOf(location.getLongitude());
        data = new busTask();
        data.execute(X,Y);
        Log.d(TAG,X+":"+Y);
        showMyLocationMarker(location);
    }

    private void showMyLocationMarker(Location location) {
        if(myLocationMarker == null){
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(new LatLng(location.getLatitude(),location.getLongitude()));
            myLocationMarker.title("*** 내 위치 ***\n");
            myLocationMarker.snippet("GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        } else{
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(new LatLng(location.getLatitude(),location.getLongitude()));
            myLocationMarker.title("*** 내 위치 ***\n");
            myLocationMarker.snippet("GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        }
    }
    @Override
    public void  onPause(){
        super.onPause();

        if(map != null){
            map.setMyLocationEnabled(false);
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        if(map != null)
            map.setMyLocationEnabled(true);
    }

    private class busTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String...params){

            try{
                return (String)downloadUrl((String) params[0],(String) params[1]);

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
                            else if(tag.equals("arsId")){
                                xpp.next();
                                arsId.add(xpp.getText().toString());
                            }
                            else if(tag.equals("stationNm")){
                                xpp.next();
                                stationNm.add(xpp.getText().toString());
                            }
                            else if(tag.equals("gpsX")){
                                xpp.next();
                                posX.add(xpp.getText().toString());
                            }
                            else if(tag.equals("gpsY")){
                                xpp.next();
                                posY.add(xpp.getText().toString());
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
                for(int i =0; i < posY.size();i++){
                    double Y = Double.valueOf(posX.get(i));
                    double X = Double.valueOf(posY.get(i));
                    myLocationMarker = new MarkerOptions();
                    myLocationMarker.position(new LatLng(X,Y));
                    myLocationMarker.title(stationNm.get(i)+"\n");
                    myLocationMarker.snippet(arsId.get(i));
                    myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus));
                    map.addMarker(myLocationMarker);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String downloadUrl(String X,String Y) throws IOException {
        HttpURLConnection con = null;
        String str = X;
        String str1 = Y;
        String locationX = URLEncoder.encode(str);
        String locationY = URLEncoder.encode(str1);
        String line = null;
        String page = "";

        String query = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByPos?serviceKey="+key+"&tmX="+locationY+"&tmY="+locationX+"&radius=500";
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
