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

public class Login extends AppCompatActivity {
    EditText username,password;
    Button btnLogin,btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(username.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User u, BmobException e) {
                        if(e == null) {
                            Toast.makeText(Login.this,"登陆成功",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Login.this,MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(Login.this,"登陆失败"+e,Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
    }
}
