package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {
    EditText username,password,nickname;
    Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nickname = findViewById(R.id.nickname);
        btnReg = findViewById(R.id.btnReg);

        //设置监听
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString().trim());
                user.setNickname(nickname.getText().toString().trim());

                if(username.getText().toString().equals("")){
                    Toast.makeText(Register.this,"用户名为空",Toast.LENGTH_LONG).show();
                }else if (password.getText().toString().equals("")){
                    Toast.makeText(Register.this,"密码为空",Toast.LENGTH_LONG).show();
                }else{
                    user.signUp(new SaveListener<User>() {

                        @Override
                        public void done(User user, BmobException e) {
                            if(e == null){
                                Toast.makeText(Register.this,"注册成功",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Register.this,Login.class));
                                finish();

                            }else{
                                Toast.makeText(Register.this,"注册失败"+e,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
