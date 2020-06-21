package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Adapter.MyCollectPushAdapter;
import com.example.Bean.Post;
import com.example.Bean.User;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Mycollect extends AppCompatActivity {
    private SwipeRefreshLayout mycollect_swipe;
    private RecyclerView mycollect_recycle;
    private ImageView back;

    List<Post> data;
    MyCollectPushAdapter myCollectPushAdapter;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollect);

        initView();
        Bmob.initialize(Mycollect.this,"7a16b3b663cf66609d568884b1bcf3bc");
        Refresh();
        mycollect_swipe.setColorSchemeColors(android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        mycollect_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
            }


        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void Refresh() {
        User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<Post> bmobQuery = new BmobQuery<Post>();
        bmobQuery.addWhereEqualTo("relation",user);
        bmobQuery.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                mycollect_swipe.setRefreshing(false);
                if(e == null){
                    data = list;
                    MyCollectPushAdapter myCollectPushAdapter = new MyCollectPushAdapter(Mycollect.this,data);
                    mycollect_recycle.setLayoutManager(new LinearLayoutManager(Mycollect.this));
                    mycollect_recycle.setAdapter(myCollectPushAdapter);

                    Toast.makeText(Mycollect.this,"获取成功",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Mycollect.this,"获取失败",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    @SuppressLint("WrongViewCast")
    private void initView() {
        mycollect_swipe = findViewById(R.id.mycollect_swipe);
        mycollect_recycle = findViewById(R.id.mycollect_recycle);
        back = findViewById(R.id.back);
    }
}
