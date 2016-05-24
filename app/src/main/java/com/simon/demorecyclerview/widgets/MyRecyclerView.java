package com.simon.demorecyclerview.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xw on 2016/5/24.
 */
public class MyRecyclerView extends RecyclerView {

    private View mCurrentView;
    private OnItemScrollChangeListener mItemScrollChangeListener;

    public static String TAG = MyRecyclerView.class.getSimpleName();

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
//        Log.i(TAG, "onScrolled: dx=" + dx + "\ndy=" + dy);
        View newView = getChildAt(0);
        if (mItemScrollChangeListener != null) {
            if (newView != null && newView != mCurrentView) {
                mCurrentView = newView;
                mItemScrollChangeListener.onChange(mCurrentView, getChildPosition(mCurrentView));
            }
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
//        状态为0时：当前屏幕停止滚动；
//        状态为1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；
//        状态为2时：随用户的操作，屏幕上产生的惯性滑动；
        super.onScrollStateChanged(state);
//        Log.i(TAG, "onScrollStateChanged: state=" + state);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        Log.i(TAG, "onScrollChanged: l=" + l + "\nt=" + t);
    }

    public void setmItemScrollChangeListener(OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public interface OnItemScrollChangeListener {
        void onChange(View view, int position);
    }
}
