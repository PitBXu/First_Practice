package com.example.tongxunlu;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Types;

/**
 * Created by xuYang on 2017/8/24.
 */

public class SqlLiteUtils {
    private SQLiteDatabase sqLiteDatabase;

    public SqlLiteUtils(Context context){
        CreateDb db = new CreateDb(context);
        sqLiteDatabase = db.getWritableDatabase();
    }
    public boolean update(String sql){
        try{
            sqLiteDatabase.execSQL(sql);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Map<String,Object>> querry(String sql) throws SQLException{
        ArrayList<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        while(cursor.moveToNext()){
            Map<String,Object> map = new HashMap<String,Object>();

            int col = cursor.getColumnCount();
            for (int i=0;i<col;i++){
                switch(cursor.getType(i)){
                    case Types.VARCHAR:
                        map.put(cursor.getColumnName(i),cursor.getString(cursor.getColumnIndex(cursor.getColumnName(i))));
                        break;
                    case Types.INTEGER:
                        map.put(cursor.getColumnName(i),cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(i))));
                        break;
                    case Types.DOUBLE:
                        map.put(cursor.getColumnName(i),cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(i))));
                        break;
                    default :
                        map.put(cursor.getColumnName(i),cursor.getString(cursor.getColumnIndex(cursor.getColumnName(i))));
                        break;
                }

            }
            /*cursor.getColumnName(i);
            map.put("id",cursor.getInt(cursor.getColumnIndex("id")));
            map.put("name",cursor.getString(cursor.getColumnIndex("name")));
            map.put("mobile",cursor.getString(cursor.getColumnIndex("mobile")));
            map.put("home",cursor.getString(cursor.getColumnIndex("home")));
            map.put("work",cursor.getString(cursor.getColumnIndex("work")));
            map.put("pager",cursor.getString(cursor.getColumnIndex("pager")));
            map.put("other",cursor.getString(cursor.getColumnIndex("other")));*/

            resultList.add(map);
        }
        return resultList;
    }
}
