package com.study.andriod.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    String[] names = {"김태형","정재엽","오연주","남궁윤희","김철언","장희성"};
    String[] ages = {"1990년생","1990년생","1991년생","1990년생","1994년생","1995년생"};
    String[] hoom = {"부천 어디어디","서울 어디어디","인천 어디어디","부산 어디어디","중국 어디어디","일본 어디어디"};
    String[] human = {"m","m","y","y","m","m"};
    String[] phone = {"010-2222-1111","010-9999-2222","010-8888-8888","010-9999-1111","010-9797-5123","010-9760-2240"};
    int[] images = {R.drawable.face1, R.drawable.face1, R.drawable.face2,
            R.drawable.face2, R.drawable.face1,R.drawable.face1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView1 = findViewById(R.id.listView1);

        //2단계
        final MyAdapter adapter = new MyAdapter();
        listView1.setAdapter(adapter);

        //4단계
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(),
                        "전화번호 : "+phone[position],
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount(){ return names.length; }

        @Override
        public Object getItem(int position){ return  names[position]; }

        @Override
        public long getItemId(int position){ return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            SingerItemView view = new SingerItemView(getApplicationContext());
            woman view2 = new woman(getApplicationContext());

            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            if(human[position].toString().equals("m")) {
                view.setName(names[position]);
                view.setAge(ages[position]);
                view.setImage(images[position]);
                view.setHoom(hoom[position]);
                layout.addView(view);
            }else if (human[position].toString().equals("y")){
                view2.setName(names[position]);
                view2.setAge(ages[position]);
                view2.setImage(images[position]);
                view2.setHoom(hoom[position]);
                layout.addView(view2);
            }

            return layout;

        }
    }
}
