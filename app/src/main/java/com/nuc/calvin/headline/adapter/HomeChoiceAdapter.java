package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据不同的 ViewType 返回不同的 ViewHolder
 * 通过 setter 方法将不同的 View 注入进 Adapter
 */
public class HomeChoiceAdapter extends RecyclerView.Adapter<HomeChoiceAdapter.ChoiceViewHolder> {

    public static final int TYPE_HEADER = 0;

    public static final int TYPE_NOMAL = 1;

    private View mHeaderView;

    private final List<Article> dataList = new ArrayList<>();

    private Context context;

    public HomeChoiceAdapter(Context context) {
        this.context = context;
    }


    public View getmHeaderView() {
        return mHeaderView;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);//插入下标0位置
    }

    public void addDataList(List<Article> list) {

        dataList.addAll(list);
        notifyDataSetChanged();
    }


   /* public void addDataList(List<Article> list, boolean append) {
        if (list != null) {
            if (!append) {
                dataList.clear();
            }
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }*/


    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NOMAL;
        }
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NOMAL;
    }

    @NonNull
    @Override
    public ChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mHeaderView != null && i == TYPE_HEADER) {
            return new ChoiceViewHolder(mHeaderView);
        }
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_home_choice_item, viewGroup, false);
        return new ChoiceViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceViewHolder choiceViewHolder, int i) {
        if (getItemViewType(i) == TYPE_HEADER) {
            return;
        }
        final int pos = getRealPosition(choiceViewHolder);//这里的 position 实际需要不包括 header
        final Article article = dataList.get(pos);
        choiceViewHolder.bindData(article);
        choiceViewHolder.itemView.setClickable(true);
        choiceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了" + pos, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 添加头部布局后的位置
     * headerView 不为空则 position - 1
     */
    private int getRealPosition(ChoiceViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        //header 不为空，则 reclclerview 的总 Count 需要 +1（把 Header 加上算一个 item）
        return mHeaderView == null ? dataList.size() : dataList.size() - 1;
    }

    public class ChoiceViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTv;

        TextView mWhereTv;

        TextView mCommentTv;

        TextView mLikeTv;

        ImageView author;

        public ChoiceViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }
            mTitleTv = itemView.findViewById(R.id.tv_title);
            mWhereTv = itemView.findViewById(R.id.tv_where);
            mLikeTv = itemView.findViewById(R.id.tv_like);
            mCommentTv = itemView.findViewById(R.id.tv_comment);
            author = itemView.findViewById(R.id.sdv_avatar);


        }

        public void bindData(Article article) {
            mTitleTv.setText("欢迎来到IT头条");
            mWhereTv.setText("czb");
            author.setImageResource(R.drawable.author);
        }
    }
}
