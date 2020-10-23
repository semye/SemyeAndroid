/*
 * Copyright (c) 2018. Semye
 */

package com.semye.android.gist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * 重写ViewPager解决在ScrollView中滑动冲突问题
 * <p>
 * Created by yesheng on 2017/1/6.
 */
public class MyViewPager extends ViewPager {

    /**
     * 触摸时按下的点
     **/
    @NonNull
    PointF downP = new PointF();

    /**
     * 触摸时当前的点
     **/
    @NonNull
    PointF curP = new PointF();

    OnSingleTouchListener onSingleTouchListener;

    public MyViewPager(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        //每次进行onTouch事件都记录当前的按下的坐标
        curP.x = event.getX();
        curP.y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //记录按下时候的坐标  
            //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变  
            downP.x = event.getX();
            downP.y = event.getY();
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰  
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰  
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            //在up时判断是否按下和松手的坐标为一个点  
            //如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick  
            if (downP.x == curP.x && downP.y == curP.y) {
                onSingleTouch();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 单击
     */
    public void onSingleTouch() {
        if (onSingleTouchListener != null) {
            onSingleTouchListener.onSingleTouch();
        }
    }

    /**
     * 创建点击事件接口
     *
     * @author wanpg
     */
    public interface OnSingleTouchListener {
         void onSingleTouch();
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }

}  
