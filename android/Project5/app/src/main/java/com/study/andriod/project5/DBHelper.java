package com.study.andriod.project5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.study.andriod.project5.즐겨찾기.Fr1_Adapter;
import com.study.andriod.project5.즐겨찾기.Fr1_Item;

public class DBHelper extends SQLiteOpenHelper {
    public Context context;
    private static final String TAG = "lecture";
    Fr1_Adapter adapter;

    static final String DB_NAME = "bus.db";
    static final int DB_VERSION = 1;

    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
        adapter = new Fr1_Adapter(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table if not exists bus (stName text, stNum text) ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i , int i1){

        try {
            db.execSQL("DROP TABLE IF EXISTS bus");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void selectAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select stName,stNum from bus";
        try{
            Cursor cursor = db.rawQuery(sql,null);

            int count = cursor.getCount();
            int i = 0;
            while (i < count){
                cursor.moveToNext();

                String stname = cursor.getString(0).toString();
                String stnum = cursor.getString(1).toString();


                Fr1_Item item = new Fr1_Item(stname,stnum);
                adapter.addItem(item);

                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public int insertData(String stName, String stNum){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select stNum from bus where stNum = '"+stNum+"'" ;
        String sql2 = "delete from bus where stNum = '"+stNum+"'";
        String sql1 = "insert into bus "+
                "(stName,stNum) values ('"+stName+"','"+stNum+"' ) ";

        try{
            Cursor cursor = db.rawQuery(sql,null);
            int count = cursor.getCount();
            if(count != 0){
                db.execSQL(sql2);
            }else {
                db.execSQL(sql1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

}
