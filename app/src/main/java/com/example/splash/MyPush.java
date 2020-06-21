package com.example.splash;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.Adapter.MyPushAdapter;
import com.example.Bean.Post;
import com.example.Bean.User;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

@SuppressLint("Registered")
public class MyPush extends AppCompatActivity {
    private SwipeMenuRecyclerView mypush_recycler;
    private SwipeRefreshLayout mypush_swipe;
    private ImageView back;

    List<Post> data;
    MyPushAdapter myPushAdapter;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypush);

        initView();
        Bmob.initialize(MyPush.this,"7a16b3b663cf66609d568884b1bcf3bc");
        Refresh();
        mypush_swipe.setColorSchemeColors(android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        mypush_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        BmobQuery<Post> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("author",user);
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                mypush_swipe.setRefreshing(false);
                if(e==null) {
                    data = list;
                    //delete 颜色
                    mypush_recycler.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
                    mypush_recycler.setSwipeMenuCreator(swipeMenuCreator);
                    mypush_recycler.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
                    myPushAdapter = new MyPushAdapter(MyPush.this, data);
                    mypush_recycler.setLayoutManager(new LinearLayoutManager(MyPush.this));
                    mypush_recycler.setAdapter(myPushAdapter);
                    } else{
                    Toast.makeText(MyPush.this, "加载失败" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //设置菜单
    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp100);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(MyPush.this)
                    .setTextColor(Color.WHITE)
                    .setBackgroundColor(Color.RED)
                    .setText("删除")
                    .setWeight(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };

    //菜单点击监听
    SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            //任何操作必须先关闭菜单，否则可能出现Item菜单错乱
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection();//菜单的位置，左滑还是右边化
            int adapterPosition = menuBridge.getAdapterPosition();// recyclerView的Item的position
            int position = menuBridge.getPosition();//菜单在RecyclerView的Item中的位置

            if(direction == SwipeMenuRecyclerView.RIGHT_DIRECTION){
                Post post = new Post();
                post.setObjectId(data.get(adapterPosition).getObjectId());
                post.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e == null){
                            data.remove(adapterPosition);//delete
                            myPushAdapter.notifyDataSetChanged();
                            Toast.makeText(MyPush.this,"删除成功",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MyPush.this,"删除失败",Toast.LENGTH_LONG).show();
                        }
                    }
                });



            }
        }
    };
    private void initView() {
        mypush_swipe = findViewById(R.id.mypush_swipe);
        mypush_recycler = findViewById(R.id.mypush_recycle);
        back = findViewById(R.id.back);
    }
}
