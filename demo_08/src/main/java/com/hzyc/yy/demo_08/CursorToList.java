package com.hzyc.yy.demo_08;

import android.database.Cursor;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-27.
 */
public class CursorToList {

    //cursor 转换成list  在适配器中进行使用了
    public List<Map<String,Object>>  toList(Cursor cursor){
        //一行记录 一个map  map key == 列名   value == 列值
        //整个表 list
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        int count = cursor.getColumnCount();
        while (cursor.moveToNext()){
            Map<String,Object> map = new HashMap<String,Object>();
            for(int i = 1; i<=count; i++){
                String columnName = cursor.getColumnName(i);
                //全取成字符串
                //String columnName = cursor.get
                int type = cursor.getType(i);
                Object columnValue = null;
                switch (type){
                    case Types.VARCHAR:
                        columnValue = cursor.getString(i);
                        break;
                    case Types.INTEGER:
                        columnValue = cursor.getInt(i);
                        break;
                    case Types.DOUBLE:
                        columnValue = cursor.getDouble(i);
                        break;
                }
                map.put(columnName,columnValue);
            }
            list.add(map);
        }

        return list;
    }
}
