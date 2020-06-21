package com.example.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager {

    private Boolean NoScroll = true;
    private void setNoScroll(boolean noScroll){
        NoScroll = noScroll;
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void scrollTo(int x,int y){
        super.scrollTo(x,y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(NoScroll){
            return false;
        }else
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(NoScroll){
            return false;
        }else
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}
