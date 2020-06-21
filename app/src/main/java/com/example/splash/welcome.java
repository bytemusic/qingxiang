package com.example.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class welcome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Timer timer = new Timer();
        timer.schedule(timerTask,2000);

        Bmob.initialize(this,"7a16b3b663cf66609d568884b1bcf3bc");

    }
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {

            BmobUser bmobUser = BmobUser.getCurrentUser(BmobUser.class);
            if (bmobUser!=null) {
                startActivity(new Intent(welcome.this, MainActivity.class));
            }else{
                startActivity(new Intent(welcome.this,Login.class));
            }
        }
    };

}
