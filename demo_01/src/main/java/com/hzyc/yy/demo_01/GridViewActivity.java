package com.hzyc.yy.demo_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GridViewActivity extends AppCompatActivity {

    private GridView gridView;
    //数据只能自己创造
    private int [] data = {R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,
            R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,
            R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,
            R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,
            R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,
            R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1,R.drawable.image1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridView = (GridView) findViewById(R.id.gridView);

        //1.准备数据  （适配器会使用）
        //2.准备适配器
        //baseAdapter

        //适配器 添加给 布局
        gridView.setAdapter(new MyAdapter());
        //添加时间
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyAdapter extends BaseAdapter{
        //1  需要适配的数据总数
        @Override
        public int getCount() {
            return data.length;
        }
        //2  position 适配器 自动加的标识从0开始 通用的
        @Override
        public Object getItem(int position) {
            return data[position];
        }
        //3 返回当前节点的ID
        @Override
        public long getItemId(int position) {
            return position;
        }
        //4 适配会根据总数调用这个方法  100个数调用100次
        //每调用一次 适配器都要取出一个数据 创建一个控件   数据放到控件中    控件放到gridview中
        //采用缓存机制   第一次的时候imageView 会new 第二次以后 直接使用原来的
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                //第一次使用这个方法
                //Activity.this == context
                imageView = new ImageView(GridViewActivity.this);
                imageView.setPadding(10,10,10,10);
            }else{
                imageView = (ImageView)convertView;
            }

            //把数据放到控件中
            imageView.setImageResource(data[position]);
            return imageView;
        }
    }
}
