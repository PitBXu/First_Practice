package com.example.tongxunlu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class insert extends AppCompatActivity {

    Button save;
    TextView name,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        save = (Button) findViewById(R.id.save);
        name = (TextView) findViewById(R.id.name);
        number = (TextView) findViewById(R.id.mobile);
        final SqlLiteUtils sqlU = new SqlLiteUtils(insert.this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sqlU.update("insert into contacts (name,mobile) values ('"+name.getText().toString().trim()+"',"+number.getText().toString().trim()+");")){
                    Toast.makeText(insert.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
