package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.Adapter.SectionsPagerAdapter;

import com.example.Fragment.FragmentChat;
import com.example.Fragment.FragmentHome;
import com.example.Fragment.FragmentMine;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;

    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        bottomNavigationBar = findViewById(R.id.bottom);

        initView();
    }

    private void initView() {
      initViewPager();
      initBottomNavigationBar();
    }

    private void initViewPager(){

        //配置ViewPager
        //set page now
        viewPager.setOffscreenPageLimit(3);
        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentChat());
        fragments.add(new FragmentMine());

        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }
    private void initBottomNavigationBar(){
        //配置底部导航栏
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//自适应大小
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.setBarBackgroundColor(R.color.whiter)
                .setActiveColor(R.color.cloerBar)
                .setInActiveColor(R.color.black);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.homepage_fill,"帖子").setInactiveIconResource(R.drawable.homepage))
                .addItem(new BottomNavigationItem(R.drawable.mobilephone_fill,"论坛").setInactiveIconResource(R.drawable.mobilephone))
                .addItem(new BottomNavigationItem(R.drawable.mine_fill,"我的").setInactiveIconResource(R.drawable.mine))
                .setFirstSelectedPosition(0)
                .initialise();//提交

    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }
    //点击哪个底部按钮
    @Override
    public void onTabReselected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    //哪一个bar
    @Override
    public void onPageSelected(int position) {
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
