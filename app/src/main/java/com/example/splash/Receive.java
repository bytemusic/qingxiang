package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Bean.Post;
import com.example.Bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class Receive extends AppCompatActivity {
    private TextView username,content,time;
    private ImageView back;
    private  ImageView collect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        initView();
        initData();
        getisrelated();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = getIntent();
                String Id = in.getStringExtra("id");
                BmobQuery<Post> bmobQuery = new BmobQuery<>();
                bmobQuery.getObject(Id, new QueryListener<Post>() {
                    @Override
                    public void done(Post post, BmobException e) {
                        if (post.getIsrelated().equals("0")){
                            Intent in = getIntent();
                            String Id = in.getStringExtra("id");
                            User user = BmobUser.getCurrentUser(User.class);
                            Post po = new Post();
                            po.setObjectId(Id);
                            po.setIsrelated("1");
                            BmobRelation relation = new BmobRelation();
                            relation.add(user);
                            po.setRelation(relation);
                            po.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e==null){
                                        collect.setImageResource(R.drawable.shoucang_black);
                                        Toast.makeText(Receive.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(Receive.this, "收藏失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            Intent in = getIntent();
                            String Id = in.getStringExtra("id");
                            User user = BmobUser.getCurrentUser(User.class);
                            Post po = new Post();
                            po.setObjectId(Id);
                            po.setIsrelated("0");
                            BmobRelation relation = new BmobRelation();
                            relation.remove(user);
                            po.setRelation(relation);
                            po.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e==null){
                                        collect.setImageResource(R.drawable.sc_normal);
                                        Toast.makeText(Receive.this, "取消成功", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(Receive.this, "取消失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    }
                });

            }
        });

    }

    private void getisrelated() {
        //得到帖子id
        Intent in = getIntent();
        String Id = in.getStringExtra("id");
        BmobQuery<Post> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(Id, new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
                if (post.getIsrelated().equals("1")){
                    //已被收藏
                    collect.setImageResource(R.drawable.shoucang_black);
                }else {
                    //无收藏

                }
            }
        });

    }

    private void initData() {
        Intent in = getIntent();
        String id = in.getStringExtra("id");
        Post po = new Post();

        BmobQuery<Post> bmobQuery= new BmobQuery<>();
        bmobQuery.getObject(id, new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
                if(e == null){
                    username.setText(post.getName());
                    content.setText(post.getContent());
                    time.setText(post.getCreatedAt());
                }else{
                    Toast.makeText(Receive.this,"获取失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        username = findViewById(R.id.username);
        content = findViewById(R.id.content);
        time = findViewById(R.id.time);
        back = findViewById(R.id.back);
        collect = findViewById(R.id.collect);
    }
}
