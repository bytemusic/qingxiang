package com.example.Fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public abstract class BaseFragment extends Fragment {

    private Context context;
    private Resources resources;
    private LayoutInflater layoutInflater;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.resources = context.getResources();
        this.layoutInflater = LayoutInflater.from(context);
    }

    private View RootView; //缓存注释
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (RootView == null) {
            RootView = inflater.inflate(getLayoutID(), container, false);
        }

        ViewGroup parent = (ViewPager) RootView.getParent();
        if (parent != null){
            parent.removeView(RootView);
        }
        return RootView;
    }

    abstract int getLayoutID();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        bindEvent();
        ininData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    // VV View leixing id constor id
    protected <VV extends View> VV findView(View view,int id){
        return view.findViewById(id);
    }
    private void ininData() {
    }

    private void bindEvent() {
    }

    private void initView(View view) {
    }
}
