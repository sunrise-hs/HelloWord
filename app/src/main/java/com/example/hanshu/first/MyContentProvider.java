package com.example.hanshu.first;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by HanShu on 2016/7/13.
 */
public class MyContentProvider extends ContentProvider {
    PersonDB helper;
    private  static final int INSERT=1;
    private  static final int UPDATE=2;
    private  static final int QUERY=3;
    private  static final int DELETE=4;
    private static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
    static{
        matcher.addURI("com.example.hanshu.first.contentResolver","insert",INSERT);
        matcher.addURI("com.example.hanshu.first.contentResolver","update",UPDATE);
        matcher.addURI("com.example.hanshu.first.contentResolver","delete",DELETE);
        matcher.addURI("com.example.hanshu.first.contentResolver","query",QUERY);
    }
    @Override
    public boolean onCreate() {
        helper=new PersonDB(getContext()) ;
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        if(matcher.match(uri)==QUERY){
        SQLiteDatabase db=helper.getReadableDatabase();
            Cursor cursor=db.query("person",strings,s,strings1,null,null,s1);
            return cursor;
        }else{
            throw new IllegalArgumentException("路径不匹配，不能查询");
        }

    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
