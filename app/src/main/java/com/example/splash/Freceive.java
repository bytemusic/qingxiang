package com.example.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Bean.Comunity;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class Freceive extends AppCompatActivity {
    private TextView c_name,c_info,c_time;

    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recievecom);

        initView();

        getinfo();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getinfo() {
        Intent in = getIntent();
        String id = in.getStringExtra("id");
        Comunity co = new Comunity();

        BmobQuery<Comunity> bmobQuery= new BmobQuery<>();
        bmobQuery.getObject(id, new QueryListener<Comunity>() {

            @Override
            public void done(Comunity comunity, BmobException e) {
                if(e == null){
                    c_name.setText(comunity.getName());
                    c_info.setText(comunity.getInfo());
                    c_time.setText(comunity.getCreatedAt());
                }else{
                    Toast.makeText(Freceive.this,"获取失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        c_name = findViewById(R.id.c_name);
        c_info = findViewById(R.id.c_info);
        c_time = findViewById(R.id.c_time);
        back = findViewById(R.id.back);
    }
}
