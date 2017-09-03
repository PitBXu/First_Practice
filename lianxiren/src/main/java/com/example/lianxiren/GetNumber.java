package com.example.lianxiren;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
/**
 * Created by xuYang on 2017/8/24.
 */
public class GetNumber {

        public static List<PhoneInfo> lists = new ArrayList<PhoneInfo>();

        public static String getNumber(Context context){
            Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
            String phoneNumber;
            String phoneName;
            while (cursor.moveToNext()) {
                phoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
                phoneName = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
                PhoneInfo phoneInfo = new PhoneInfo(phoneName, phoneNumber);
                lists.add(phoneInfo);
                System.out.println(phoneName+phoneNumber);
            }
            return null;
        }
}

