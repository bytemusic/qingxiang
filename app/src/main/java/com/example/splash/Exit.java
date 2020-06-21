package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cn.bmob.v3.BmobUser;

public class Exit extends AppCompatActivity {
    private Button exit;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();
                startActivity(new Intent(Exit.this, Login.class));
                finish();
            }
        });

    }

    private void initView() {
        exit = findViewById(R.id.exit);
        back = findViewById(R.id.back);
    }
}
