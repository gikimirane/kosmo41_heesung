package com.study.andriod.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    TextView textView1;

    String[] items = {"mike", "angel","crow","john","ginnie","sally","cohen","rice"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,items);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템 선택되었을때 호출
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                textView1.setText(items[position]);
            }
            //아무것도 선택되지 않았을 때 호출
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
                textView1.setText("");
            }
        });
    }
}
