package com.example.splash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

@SuppressLint("Registered")
public class Mine extends AppCompatActivity {
    private ImageView back;
    private TextView my_name;
    private TextView my_nickname;
    private TextView my_push;
    private TextView my_com;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        getInfo();
    }

    private void getInfo() {
        User user = BmobUser.getCurrentUser(User.class);
        String Id = user.getObjectId();
        BmobQuery<User> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(Id,    new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    my_name.setText(user.getUsername());
                    my_nickname.setText(user.getNickname());
                }else{
                    Toast.makeText(Mine.this,"加载失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        back = findViewById(R.id.back);
        my_name = findViewById(R.id.my_name);
        my_nickname = findViewById(R.id.my_nickname);
//        my_push = findViewById(R.id.my_push);
//        my_com = findViewById(R.id.my_com);
    }

}