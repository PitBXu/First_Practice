package com.example.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class goodsCar extends AppCompatActivity {

    private ListView listView;
    private ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_car);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new goodsCar.MyAdapter());

        Intent intent = getIntent();
        int listnum = intent.getIntExtra("listnum",0);
        Toast.makeText(goodsCar.this, "数据数量是"+listnum, Toast.LENGTH_SHORT).show();

        image1 = (ImageView) findViewById(R.id.imageView2);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backValue = 123;
                Intent intent1 = new Intent(goodsCar.this,MainActivity.class);
                intent1.putExtra("back",backValue);
                setResult(400,intent1);
                finish();
            }
        });
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
                view = LayoutInflater.from(goodsCar.this).inflate(R.layout.image_text_two, null);
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
