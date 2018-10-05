package com.study.android.googlemapex3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    private static final LatLng SEOUL   = new LatLng(37.566535, 126.977969);
    private static final LatLng DAEJEON = new LatLng(36.350412, 127.384548);
    private static final LatLng SUWEON  = new LatLng(37.263573, 127.028601);
    private static final LatLng BUSAN   = new LatLng(35.179554, 129.075642);
    private static final LatLng KWANGJU = new LatLng(35.159545, 126.852601);

    SupportMapFragment mapFragment;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG,"GoogleMap is ready");

                map = googleMap;

                requestMapDraw();
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void requestMapDraw(){
        //폴리라인 그리기
        PolylineOptions myLine = new PolylineOptions()
                .width(5)
                .color(Color.RED);
        map.addPolyline((myLine)
                .add(SEOUL,BUSAN,KWANGJU,DAEJEON,SUWEON));

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(SEOUL,7.0f));
    }
}
