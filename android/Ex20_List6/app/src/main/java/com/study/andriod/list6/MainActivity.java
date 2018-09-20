package com.study.andriod.list6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    SingerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new SingerAdapter(this);

        SingerItem item1 = new SingerItem("홍길동","010-1234-5678",R.drawable.face1);
        adapter.addItem(item1);

        SingerItem item2 = new SingerItem("전우치","010-5555-6666",R.drawable.face2);
        adapter.addItem(item2);

        SingerItem item3 = new SingerItem("이순신","010-8888-1111",R.drawable.face3);
        adapter.addItem(item3);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),
                        "selected : "+item.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
