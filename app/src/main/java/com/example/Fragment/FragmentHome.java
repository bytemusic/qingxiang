package com.example.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.Adapter.HomeAdapter;
import com.example.Bean.Post;
import com.example.splash.R;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FragmentHome extends androidx.fragment.app.Fragment {

    private RecyclerView rv;
    private SwipeRefreshLayout srlayout;
    private TextView HelloHome;

    List<Post> data;

    public FragmentHome(RecyclerView rv, SwipeRefreshLayout srlayout) {
        this.rv = rv;
        this.srlayout = srlayout;
    }

    public FragmentHome() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmenthome,container,false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //逻辑处理
        initView();
        Bmob.initialize(getActivity(),"7a16b3b663cf66609d568884b1bcf3bc");

        //初始化刷新
        Refresh();
        srlayout.setColorSchemeColors(android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        srlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()   {
                Refresh();
            }
        });
    }

    private void Refresh() {
        BmobQuery<Post> po = new BmobQuery<Post>();
        po.order("-createAt");
        po.setLimit(1000);
        po.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                srlayout.setRefreshing(false);

                if (e == null){
                    data = list;
                    HomeAdapter homeAdapter = new HomeAdapter(getActivity(), data);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(homeAdapter);
                }else{
                    srlayout.setRefreshing(false);
                    Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        rv = getActivity().findViewById(R.id.recyclerview);
        srlayout = getActivity().findViewById(R.id.swipe);
        HelloHome = getActivity().findViewById(R.id.HelloHome);
    }
}
