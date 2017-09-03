package com.example.tongxunlu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<Map<String,Object>> list;
    ListView listView;
    ImageButton insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqlLiteUtils sqlU = new SqlLiteUtils(MainActivity.this);

        list = sqlU.querry("select * from contacts;");
        listView = (ListView) findViewById(R.id.list1);

        listView.setAdapter(new MyAdapter());
        insert = (ImageButton) findViewById(R.id.imageButton);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,insert.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+list.get(i).get("mobile").toString()));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });

    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView == null){
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.img_txt,null);
            }else{
                view = convertView;
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.photo);
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView mobile = (TextView) view.findViewById(R.id.mobile);

            imageView.setImageResource(R.drawable.touxiang);
            name.setText(list.get(position).get("name").toString());
            mobile.setText(list.get(position).get("mobile").toString());

            return view;
        }
    }
}
