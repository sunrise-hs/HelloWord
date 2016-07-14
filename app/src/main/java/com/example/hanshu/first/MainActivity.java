package com.example.hanshu.first;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
   Button b1,b2,b3,b4;
  ListView lv;
    PersonDB helper;
    List<Person> list_infor=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new PersonDB(this);
        b1=(Button)findViewById(R.id.b1);
        lv=(ListView)findViewById(R.id.listView);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=helper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name","李四");
                values.put("number","2222222");

              Long i=  db.insert("person",null,values);

                ContentValues values1=new ContentValues();
                values1.put("name","张三");
                values1.put("number","111111110");
                 db.insert("person",null,values1);

                ContentValues values2=new ContentValues();
                values2.put("name","王五");
                values2.put("number","3333333");
                db.insert("person",null,values2);

                ContentValues values3=new ContentValues();
                values3.put("name","丁一");
                values3.put("number","4444444");
                db.insert("person",null,values3);

                ContentValues values4=new ContentValues();
                values4.put("name","赵四");
                values4.put("number","5555555");

                db.insert("person",null,values4);




                System.out.println("增加数据09090909");
                if(i==-1){
                    Toast.makeText(MainActivity.this, "插入失败", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });


        b3=(Button)findViewById(R.id.dele);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=helper.getWritableDatabase();
                int i=db.delete("Person","id=?",new String[]{"10"});
                if(i==0){
                    Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                }

            }
        });
        b4=(Button)findViewById(R.id.upda);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=helper.getWritableDatabase();
                ContentValues values6=new ContentValues();
                values6.put("name","房丽");
                values6.put("number","6666666");
                db.update("Person",values6,"id=?",new String[]{"6"});
            }
        });
        b2=(Button)findViewById(R.id.sele);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=helper.getWritableDatabase();
                Cursor cursor= db.query("Person", null, null, null, null, null, null);
                list_infor=new ArrayList<Person>();
                while(cursor.moveToNext()){
                    Person infor=new Person();
                    int id=cursor.getInt(cursor.getColumnIndex("id"));
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                    String number=cursor.getString(cursor.getColumnIndex("number"));
                    infor.setId(id);
                    infor.setName(name);
                    infor.setNumber(number);
                    list_infor.add(infor);
                }
                cursor.close();
                db.close();

                lv.setAdapter(new MyAdapter());
            }
        });
    }
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list_infor.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tv=new TextView(MainActivity.this);
            tv.setText(list_infor.get(i).toString());
            return tv;
        }
    }

}
