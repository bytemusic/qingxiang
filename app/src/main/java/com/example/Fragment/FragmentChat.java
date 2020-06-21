package com.example.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.Adapter.ChatAdapter;
import com.example.Bean.Comunity;
import com.example.Bean.Post;
import com.example.splash.PushComunity;
import com.example.splash.PushContent;
import com.example.splash.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FragmentChat extends androidx.fragment.app.Fragment {

    private RecyclerView rec_chat;
    private SwipeRefreshLayout swipe_chat;
    private TextView HelloHome;

    private FloatingActionButton add,addcontent,addcomunity;
    private PopupWindow pop;
    private View view;
    private RelativeLayout rvlayout;
    private ChatAdapter chatAdapter;

    List<Comunity> data;

    public FragmentChat(RecyclerView rec_chat,SwipeRefreshLayout swipe_chat){
        this.swipe_chat = swipe_chat;
        this.rec_chat = rec_chat;
    }

    public FragmentChat() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentchat,container,false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //popuWindow需要一个view对象
        view = getLayoutInflater().inflate(R.layout.pop_item,null);
        //逻辑处理
        initView();
        Bmob.initialize(getActivity(),"7a16b3b663cf66609d568884b1bcf3bc");

        //初始化刷新
        Refresh();
        swipe_chat.setColorSchemeColors(android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        swipe_chat.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()   {
                Refresh();
            }
        });
        //对flaotbutton监听
        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                pop = new PopupWindow(view,250,700,true);
                pop.setOutsideTouchable(true);
                pop.setFocusable(true);
                pop.showAtLocation(rvlayout, Gravity.CENTER,530,350);
            }
        });

        addcontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PushContent.class));
            }
        });

        addcomunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PushComunity.class));
            }
        });
    }

    private void Refresh() {
        BmobQuery<Comunity> co = new BmobQuery<Comunity>();
        co.order("-createAt");
        co.setLimit(1000);
        co.findObjects(new FindListener<Comunity>() {
            @Override
            public void done(List<Comunity> list, BmobException e) {
                swipe_chat.setRefreshing(false);

                if (e == null){
                    data = list;
                    ChatAdapter chatAdapter = new ChatAdapter(getActivity(), data);
                    rec_chat.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rec_chat.setAdapter(chatAdapter);
                }else{
                    swipe_chat.setRefreshing(false);
                    Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        rec_chat = getActivity().findViewById(R.id.rec_chat);
        swipe_chat = getActivity().findViewById(R.id.swipe_chat);
        add = getActivity().findViewById(R.id.add);
        addcontent =view.findViewById(R.id.addContent);
        addcomunity =view.findViewById(R.id.addComunity);
        rvlayout = getActivity().findViewById(R.id.chat_relative);
    }
}
