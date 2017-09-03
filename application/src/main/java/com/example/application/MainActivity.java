package com.example.application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ImageView image2;
    private EditText save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView); //使用的activity_main
        listView.setAdapter(new MyAdapter());
        image2 = (ImageView) findViewById(R.id.imageView10);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,goodsCar.class);
                intent.putExtra("listnum",16);
                startActivityForResult(intent,200);
            }
        });
        save = (EditText) findViewById(R.id.editText);
        SharedPreferences sp = getSharedPreferences("data",0);
        String value = sp.getString("value","nothing");

        if("nothing".equals(value)){

        }else{
            save.setText(value);
        }
        registerForContextMenu(listView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String value = save.getText().toString().trim();
        boolean bol = false;
        if(!value.equals("")){
            SharedPreferences sp = getSharedPreferences("data",0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("value",value);

            bol = editor.commit();

            Toast.makeText(MainActivity.this, "保护的状态="+bol, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "保护的状态="+bol, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 400){
            int backValue = data.getIntExtra("back",0);
            //Toast.makeText(MainActivity.this, backValue, Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, ""+backValue, Toast.LENGTH_SHORT).show();
        }
    }

    public List<Map<String,Object>> getList(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 0;i<=16; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("photo",R.drawable.image1);
            map.put("name","数据"+i);
            map.put("price","价格="+i);
            map.put("beizhu","小米手机");
            map.put("rating",i%5 + 1);
            list.add(map);
        }
        return list;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //填充菜单文件
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_one,menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //显示什么菜单文件呢？
        MenuInflater menuInflater = getMenuInflater();
        //填充菜单文件
        menuInflater.inflate(R.menu.menu_one,menu);
        return super.onCreateOptionsMenu(menu);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return getList().size();
        }

        @Override
        public Object getItem(int position) {
            return getList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            if (convertView == null) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.image_text_two, null);
            } else {
                view = convertView;
            }

            ImageView imageView = (ImageView) view.findViewById(R.id.photo);
            TextView textView1 = (TextView) view.findViewById(R.id.name);
            TextView textView2 = (TextView) view.findViewById(R.id.price);
            TextView textView3 = (TextView) view.findViewById(R.id.beizhu);
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

            imageView.setImageResource(Integer.parseInt(getList().get(position).get("photo").toString()));
            textView1.setText(getList().get(position).get("name").toString());
            textView2.setText(getList().get(position).get("price").toString());
            textView3.setText(getList().get(position).get("beizhu").toString());
            ratingBar.setRating(Float.parseFloat(getList().get(position).get("rating").toString()));

            return view;
        }
    }
}
