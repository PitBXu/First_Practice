package com.hzyc.yy.demo_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View 所有控件的父类  表示所有的控件
        //三次开发
        username = (EditText) findViewById(R.id.username);
        //alt+回车  万能快捷键  解决方案
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //内部类中 匿名内部类
                    String name = username.getText().toString().trim();

                    String psd = password.getText().toString().trim();

                    if("admin".equals(name) && "admin".equals(psd)){
                        //调试输出
                        //Toast 排队打印的
                        //LogCat 日志系统
                        //打印的信息的时候 需要加级别  标识  信息
                        //info 普通信息
                        //debug 调试信息
                        //warn 警告信息
                        //error 错误信息 影响程序运行
                      /*  Toast.makeText(MainActivity.this, "登录成功1", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "登录成功2", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "登录成功3", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "登录成功4", Toast.LENGTH_SHORT).show();*/

                        Log.i("基本信息","登录成功1");
                        Log.i("基本信息","登录成功2");
                        Log.i("基本信息","登录成功3");
                        Log.i("基本信息","登录成功4");

                    }else{
                        Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                }
        });
    }
}
