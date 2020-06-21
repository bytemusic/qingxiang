package com.example.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.Bean.User;
import com.example.splash.Exit;
import com.example.splash.Login;
import com.example.splash.Mine;
import com.example.splash.MyComunity;
import com.example.splash.MyPush;
import com.example.splash.Mycollect;
import com.example.splash.R;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class FragmentMine extends androidx.fragment.app.Fragment {
    private TextView username;
    //private TextView nickname;
    private LinearLayout mine;
    private LinearLayout my_push;
    private LinearLayout my_collect;
    private LinearLayout setting;
//    private LinearLayout myComunity;
    //private Button exit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentmine,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        //加载我的信息
        getMyinfo();

        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Mine.class));
            }
        });
        my_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyPush.class));
            }
        });
//        myComunity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), MyComunity.class));
//            }
//        });
        my_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Mycollect.class));
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Exit.class));
            }
        });

    }

    private void getMyinfo() {
        BmobUser bu = BmobUser.getCurrentUser(BmobUser.class);
        String Id = bu.getObjectId();
        BmobQuery<User> query = new BmobQuery<User>();
        query.getObject(Id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e == null){
                    username.setText(user.getUsername());
//                    nickname.setText(user.getNickname());
                }else{
                    Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        username = getActivity().findViewById(R.id.username);
//        nickname = getActivity().findViewById(R.id.nickname);
        mine = getActivity().findViewById(R.id.mine);
        my_push = getActivity().findViewById(R.id.my_push);
        my_collect = getActivity().findViewById(R.id.myCollect);
        setting = getActivity().findViewById(R.id.Setting);
//        myComunity = getActivity().findViewById(R.id.myComunity);
    }
}
