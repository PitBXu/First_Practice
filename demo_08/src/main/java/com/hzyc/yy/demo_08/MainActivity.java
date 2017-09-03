package com.hzyc.yy.demo_08;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createDataBase, add, update,addProvider,queryProvider,readContract;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDataBase = (Button) findViewById(R.id.createDataBase);
        add = (Button) findViewById(R.id.add);
        update = (Button) findViewById(R.id.update);
        addProvider = (Button) findViewById(R.id.addProvider);
        queryProvider = (Button) findViewById(R.id.queryProvider);
        readContract = (Button) findViewById(R.id.readContract);

        createDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDb db = new CreateDb(MainActivity.this);

                SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDb db = new CreateDb(MainActivity.this);

                //getReadableDatabase //如果磁盘空间  （手机控件 20480 kb）    sqLiteDatabase 还是能操作数据库的  readonly  （select）
                //getWriterbleDatabase//如果磁盘空间  （手机控件 20480 kb）    操作不了数据库
                SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
                //SQLiteDatabase sqLiteDatabase1 = db.getWritableDatabase();

                //sql
                //推荐我们使用这样的  insert into table values (?,?,?);
             /*   sqLiteDatabase.execSQL("insert into userinfo (username,password) values ('张三','123456')");
                sqLiteDatabase.execSQL("insert into userinfo (username,password) values ('李四','123456')");
                sqLiteDatabase.execSQL("insert into userinfo (username,password) values ('王五','123456')");*/

                //sql提供非SQL模式  （一条sql语句不会写  也能操作数据库   hibernate（orm 非SQL模式）==mybatis（sql语句） （持久层  数据访问和数据交互））

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDb db = new CreateDb(MainActivity.this);
                SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();


                //insert into userinfo (username,password) values ('张三','123456')
                //sqLiteDatabase.execSQL("静态SQL");//sql模式

                //insert into userinfo (username,password) values ('张三','123456')
                //sqLiteDatabase.execSQL("insert into userinfo (username,password) values (?,?)",new Object[]{"张三" ,123456});

                //非sql模式 (dml 增删改  不需要写SQL)
                //contentValue 添加的数据集合 == map   == contentValue==一行数据
              /*  ContentValues contentValues = new ContentValues();
                contentValues.put("username","小明");
                contentValues.put("password","123456");

                //i == 添加成功那行数据的主键
                //主键  UUID格式  16进制的无序字符串组成逐渐   1231231231231
                //添加的语句比较简单  因为没有条件
                long i = sqLiteDatabase.insert("userinfo",null,contentValues);
                Log.i("数据库动态",""+i);*/

               /* ContentValues contentValues = new ContentValues();
                contentValues.put("username", "小强");
                contentValues.put("password", "admin");
                long i = sqLiteDatabase.update("userinfo", contentValues, "id = ?", new String[]{"1"});
                Log.i("数据库动态", "" + i);*/
                //sqLiteDatabase.delete();


                //查询
                //sqLiteDatabase.query(false,"userinfo",new String[]{"username,password"},"username = ?",new String[]{"小强"},"name","",)
                Cursor cursor = sqLiteDatabase.query("userinfo",new String[]{"username","password"},null,null,null,null,null,null);
                while(cursor.moveToNext()){
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    String password = cursor.getString(cursor.getColumnIndex("password"));
                    Log.i("数据库动态", "username=" + username + " password="+password);
                }

            }
        });

        addProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //访问 内容提供者  添加数据（数据库中添数据）
                //得到cr 属于每一个App自带的功能  直接从父类中获取
                ContentResolver cr = getContentResolver();
                //cr可以发送请求了  写URI
                //因为我们要访问08 需要写自己的URI
                //请求 联系人  短信。。。  URI  默认URI
                //ContactsContract.AUTHORITY_URI
              /*  String path = "content://com.hzyc.yy.demo_08.MyContentProvider/userinfo";
                ContentValues contentValues = new ContentValues();
                contentValues.put("username", "大力娃");
                contentValues.put("password", "dlw");
                Uri uri = cr.insert(Uri.parse(path),contentValues); //来自于CP
                Log.i("内容提供者信息",""+uri);*/
            /*   Cursor cursor =  cr.query(ContactsContract.AUTHORITY_URI);

                cursor.getString(cursor.getColumnIndex())*/

                //走删除
             /*   String path = "content://com.hzyc.yy.demo_08.MyContentProvider/userinfo/5";
                Uri uri = Uri.parse(path);
                //String mime =  cr.getType(uri);
                cr.delete(uri);*/


            }
        });

        queryProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver cr = getContentResolver();
                //1.查整个表
              /*  String path = "content://com.hzyc.yy.demo_08.MyContentProvider/userinfo";
                Uri uri = Uri.parse(path);
                Cursor cursor = cr.query(uri,new String []{"username","password"},null,null,null);
                while(cursor.moveToNext()){
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    String password = cursor.getString(cursor.getColumnIndex("password"));
                    Log.i("数据库动态", "username=" + username + " password="+password);
                }*/
                //2.查一行记录
                String path = "content://com.hzyc.yy.demo_08.MyContentProvider/userinfo/2";
                Uri uri = Uri.parse(path);
                Cursor cursor = cr.query(uri,new String []{"username","password"},null,new String[]{"2"},null);
                while(cursor.moveToNext()){
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    String password = cursor.getString(cursor.getColumnIndex("password"));
                    Log.i("数据库动态", "username=" + username + " password="+password);
                }
            }
        });

        readContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到客户端
                ContentResolver cr  = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME},null,null,null);
                while(cursor.moveToNext()){
                    String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String password = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.i("数据库动态", "username=" + username + " password="+password);
                }
            }
        });
    }
}
