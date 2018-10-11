package com.study.andriod.project6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.study.andriod.project6.즐겨찾기.Fr1_Adapter;
import com.study.andriod.project6.즐겨찾기.Fr1_Item;


public class Fragment1 extends Fragment {
    private static final String TAG = "lecture";
    DBHelper mydb;
    Fr1_Adapter adapter;
    ListView listView1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fragment1,container,false);

        adapter = new Fr1_Adapter(getActivity());

        mydb = new DBHelper(getActivity());
        listView1 = rootView.findViewById(R.id.listView11);
        listView1.setAdapter(mydb.adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Fr1_Item item = (Fr1_Item) mydb.adapter.getItem(position);
                //이게 다른액티비티로 값보내주는거. 인텐트로 받으면됨 그쪽에서도. 액티비티 보낼곳 이름 바꾸기 와서.
                Intent intent = new Intent(getActivity().getApplicationContext(),busStActivity.class);
                intent.putExtra("srId",item.getStNum());
                intent.putExtra("stName",item.getStName());
                startActivityForResult(intent,1);

            }
        });

        mydb.selectAllData();

        return rootView;
    }

}
