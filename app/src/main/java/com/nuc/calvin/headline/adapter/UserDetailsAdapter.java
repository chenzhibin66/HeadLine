package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.LoginActivity;
import com.nuc.calvin.headline.bean.ui.Details;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.DetailsHolder> {
    private List<Details> detailsList = new ArrayList<>();
    Context context;

    public UserDetailsAdapter(Context context) {
        this.context = context;
    }

    public void addDetails(List<Details> list) {
        detailsList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_user_details, viewGroup, false);
        DetailsHolder detailsHolder = new DetailsHolder(view);
        return detailsHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final DetailsHolder detailsHolder, int i) {
        final int pos = detailsHolder.getLayoutPosition();
        final Details details = detailsList.get(pos);
        if (detailsHolder instanceof DetailsHolder) {
            detailsHolder.bindDetails(details);
        }
        detailsHolder.itemView.setClickable(true);
        detailsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (pos) {
                    case 0:
                        Toast.makeText(context, "点击了" + pos, Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(context, "点击了" + pos, Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(context, "点击了" + pos, Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(context, "点击了" + pos, Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(context, "点击了" + pos, Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class DetailsHolder extends RecyclerView.ViewHolder {
        ImageView details_img;
        TextView details_title;

        public DetailsHolder(@NonNull View itemView) {
            super(itemView);
            details_img = itemView.findViewById(R.id.iv_user_details);
            details_title = itemView.findViewById(R.id.title_user_details);
        }

        public void bindDetails(Details details) {
            details_img.setImageResource(details.getDetailImgId());
            details_title.setText(details.getDetails_title());
        }
    }
}
