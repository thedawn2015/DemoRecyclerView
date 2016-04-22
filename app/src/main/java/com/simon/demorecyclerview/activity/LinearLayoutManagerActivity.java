package com.simon.demorecyclerview.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.simon.demorecyclerview.R;
import com.simon.demorecyclerview.model.User;
import com.simon.demorecyclerview.adapter.LinearLayoutManagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LinearLayoutManagerActivity extends AppCompatActivity {

    private ImageView mImgBigView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManagerAdapter mManagerAdapter;
    private List<User> mUserList;
    private List<Integer> mHeaderList;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, LinearLayoutManagerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_manager);

        assignViews();
        initData();
        refreshData();
    }

    /**
     * 绑定
     */
    private void assignViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.linear_recycler_view);
        mImgBigView = (ImageView) findViewById(R.id.linear_iv_big_img);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mUserList = new ArrayList<>();
        mHeaderList = new ArrayList<>();
        mHeaderList.add(R.drawable.draw1);
        mHeaderList.add(R.drawable.draw2);
        mHeaderList.add(R.drawable.draw3);

        User user;
        for (int i = 0; i < 3; i++) {
            user = new User();
            user.setHeaderImage(mHeaderList.get(i));
            user.setName("xiao" + i);
            mUserList.add(user);
        }

        //设置布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mManagerAdapter = new LinearLayoutManagerAdapter(this, mUserList);
        //设置单击事件监听
        mManagerAdapter.setmOnItemClickListener(new LinearLayoutManagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View viewItem, int position) {
                Toast.makeText(LinearLayoutManagerActivity.this, "viewItem " + position, Toast.LENGTH_SHORT).show();
            }
        });
        //设置适配器
        mRecyclerView.setAdapter(mManagerAdapter);
    }

    /**
     * 数据刷新
     */
    private void refreshData() {

    }
}
