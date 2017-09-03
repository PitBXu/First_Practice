package com.hzyc.yy.demo_08;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017-08-27.
 * 操作数据库 把数据库中的数据对外开放
 */
public class MyContentProvider extends ContentProvider{

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private CreateDb db;

    static{
        uriMatcher.addURI("com.hzyc.yy.demo_08.MyContentProvider","userinfo",1);

        uriMatcher.addURI("com.hzyc.yy.demo_08.MyContentProvider","userinfo/#",2);

    }

    //AndroidManifest.xml  被加载  方法就调用了 初始化的方法
    @Override
    public boolean onCreate() {
        db = new CreateDb(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int i = uriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor;
        switch (i){
            case 1:
                cursor = sqLiteDatabase.query("userinfo",projection,selection,selectionArgs,null,null,sortOrder);
                return cursor;
            case 2:
                cursor = sqLiteDatabase.query("userinfo",projection,"id = ?",selectionArgs,null,null,sortOrder);
                return cursor;
        }
        return null;
    }

    @Nullable
    @Override
    //mime 文件类型
    //用来帮助操作之前判断 URI 操作的是一个或一堆数据
    //用来帮助操作之间判断 URI里面是否存在mime类型
    public String getType(Uri uri) {
        int i = uriMatcher.match(uri);
        switch (i){
            case 1:
                return "vnd.android.cursor.dir/";
            case 2:
                return "vnd.android.cursor.item";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i("内容提供者信息",""+uri);
        //1.解析URI 判断 操作整个表  还是一行记录
        //int i = uriMatcher.match(uri);
        int i = uriMatcher.match(uri);
        Log.i("内容提供者信息",""+i);
        switch (i){
            case 1:
                SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
                long  id = sqLiteDatabase.insert("userinfo",null,values);
                //重新构建URI
                Uri uri1 = ContentUris.withAppendedId(uri,id);
                return uri1;
            case 2:
                return null;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int result = 0;

        int i = uriMatcher.match(uri);
        Log.i("内容提供者信息",""+i);
        switch (i){
            case 1:

                return result;
            case 2:
                SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
                result = sqLiteDatabase.delete("userinfo","id = ?",new String[]{"5"});
                return result;
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
