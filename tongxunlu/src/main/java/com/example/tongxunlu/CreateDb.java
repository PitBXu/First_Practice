package com.example.tongxunlu;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by xuYang on 2017/8/24.
 */
public class CreateDb extends SQLiteOpenHelper {

    private static final int VERSION = 2; //版本号
    private static final String DB_NAME = "data.db";

    public CreateDb(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    //建表
    //调用 getReadableDatabase 之后 只执行一次
    @Override
    public void onCreate(SQLiteDatabase db) {
        //sql语句  语法类似于 mysql
        boolean bol = false;
        db.execSQL("create table contacts (id integer primary key autoincrement,name varchar(10),mobile varchar(100),home varchar(100),work varchar(100),pager varchar(100),other varchar(100))");
        bol = true;
        Log.i("数据库动态","创建状态="+bol);
    }

    //更新表  参照版本号
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        boolean bol = false;
        db.execSQL("insert into contacts (name,mobile) values ('张三','10086');");
        bol = true;
        Log.i("数据库动态","编辑状态="+bol);
    }
}
