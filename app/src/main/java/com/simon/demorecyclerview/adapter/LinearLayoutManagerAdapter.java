package com.simon.demorecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simon.demorecyclerview.R;
import com.simon.demorecyclerview.model.User;

import java.util.List;

/**
 * Created by Coder on 2016/4/20.
 */
public class LinearLayoutManagerAdapter extends RecyclerView.Adapter<LinearLayoutManagerAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUserList;

    public LinearLayoutManagerAdapter(Context context, List<User> userList) {
        this.mContext = context;
        this.mUserList = userList;
    }


    /**
     * 创建viewHolder
     *
     * @param viewGroup
     * @param position
     * @return
     */
    @Override
    public LinearLayoutManagerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.linear_item, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * 设置值
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(LinearLayoutManagerAdapter.ViewHolder viewHolder, int position) {
        //为viewHolder绑定数据
        viewHolder.bind(mUserList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private ImageView ivHeader;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivHeader = (ImageView) itemView.findViewById(R.id.item_iv_header);
            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);
        }

        public void bind(User user, final int position) {
            ivHeader.setImageResource(user.getHeaderImage());
            tvName.setText(user.getName());

            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v, position);
                    }
                });
            }
        }
    }

    /**---------------------------------------单击事件------------------------------------------**/
    /**
     * 单击事件接口
     */
    public interface OnItemClickListener {
        void onItemClick(View viewItem, int position);
    }

    /**
     * 单击事件对象
     */
    private OnItemClickListener mOnItemClickListener;

    /**
     * 单击事件设置
     *
     * @param mOnItemClickListener
     */
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
