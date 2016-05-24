package com.simon.demorecyclerview.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.simon.demorecyclerview.R;
import com.simon.demorecyclerview.adapter.LinearLayoutManagerAdapter;
import com.simon.demorecyclerview.model.User;
import com.simon.demorecyclerview.utils.UserUtil;
import com.simon.demorecyclerview.widgets.MyRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LinearLayoutManagerActivity extends AppCompatActivity {


    @BindView(R.id.linear_iv_big_img)
    ImageView linearIvBigImg;
    @BindView(R.id.linear_recycler_view)
    MyRecyclerView linearRecyclerView;

    private ImageView mImgBigView;
    private MyRecyclerView mRecyclerView;
    private LinearLayoutManagerAdapter mManagerAdapter;
    private List<User> mUserList;
    public static String TAG = LinearLayoutManagerActivity.class.getSimpleName();


    public static void launcher(Context context) {
        context.startActivity(new Intent(context, LinearLayoutManagerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_manager);
        ButterKnife.bind(this);

        assignViews();
        refreshData();
    }

    /**
     * 绑定
     */
    private void assignViews() {
        mRecyclerView = (MyRecyclerView) findViewById(R.id.linear_recycler_view);
        mImgBigView = (ImageView) findViewById(R.id.linear_iv_big_img);


        //设置布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mManagerAdapter = new LinearLayoutManagerAdapter(this);
        //设置单击事件监听
        mManagerAdapter.setmOnItemClickListener(new LinearLayoutManagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LinearLayoutManagerAdapter.ViewHolder holder, int position) {
                Toast.makeText(LinearLayoutManagerActivity.this, "viewItem " + position, Toast.LENGTH_SHORT).show();
                mImgBigView.setImageResource(holder.getUser().getHeaderImage());
            }
        });
        //设置适配器
        mRecyclerView.setAdapter(mManagerAdapter);

        mRecyclerView.setmItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                Log.i(TAG, "onChange: position=" + position);
                mImgBigView.setImageResource(mUserList.get(position).getHeaderImage());
            }
        });
    }

    /**
     * 数据刷新
     */
    private void refreshData() {
        mUserList = UserUtil.getUserList();
        Observable
                .create(new Observable.OnSubscribe<User>() {
                    @Override
                    public void call(Subscriber<? super User> subscriber) {
                        for (User user : mUserList) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            subscriber.onNext(user);
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(User response) {
                        Log.d(TAG, "onNext: ");
                        mManagerAdapter.addItem(response);
                    }
                });
    }
}
