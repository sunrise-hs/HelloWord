package com.example.hanshu.first;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HanShu on 2016/7/12.
 */
public class PersonDB extends SQLiteOpenHelper {
    public PersonDB(Context context){
        super(context,"person.db",null,5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table person (id integer primary key autoincrement,name varchar(20),number varchar(20))");
        System.out.print("数据库创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("数据库升级成功");
    }
}
