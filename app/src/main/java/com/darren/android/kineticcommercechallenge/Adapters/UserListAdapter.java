package com.darren.android.kineticcommercechallenge.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darren.android.kineticcommercechallenge.Activities.UserInfoActivity;
import com.darren.android.kineticcommercechallenge.DataModels.Result;
import com.darren.android.kineticcommercechallenge.Listeners.OnLoadMoreListener;
import com.darren.android.kineticcommercechallenge.R;
import com.darren.android.kineticcommercechallenge.Utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darren on 6/26/2017.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private static final String TAG = "UserListAdapter";

    private Context context;
    private ArrayList<Result> users;
    private OnLoadMoreListener onLoadMoreListener;
    private int lastVisibleItem;
    private int totalItemCount;

    public UserListAdapter(@NonNull Context context, @NonNull ArrayList<Result> users, @NonNull RecyclerView recyclerView) {
        this.context = context;
        this.users = users;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (totalItemCount <= lastVisibleItem + 1) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_user_brief_info_item, parent, false);

        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result user = users.get(position);

        if (user != null) {
            if (holder.userFullNameTV != null) {
                holder.userFullNameTV.setText(capitalize(user.getName().getFirst()) + " " + capitalize(user.getName().getLast()));
            }
            if (holder.userThumbnailIV != null) {
                Glide.with(context)
                        .load(user.getPicture().getThumbnail())
                        .into(holder.userThumbnailIV);
            }
        }
        holder.container.setOnClickListener(clickListener);
        holder.container.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            int position = holder.getAdapterPosition();
            Log.d(TAG, "onClick " + position);

            Context context = view.getContext();
            Intent selectedUserIntent = new Intent(context, UserInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.USER_NAME_TAG, users.get(position).getName());
            bundle.putParcelable(Constants.USER_LOCATION_TAG, users.get(position).getLocation());
            bundle.putParcelable(Constants.USER_PICTURE_TAG, users.get(position).getPicture());
            selectedUserIntent.putExtras(bundle);
            selectedUserIntent.putExtra(Constants.USER_EMAIL_TAG, users.get(position).getEmail());
            selectedUserIntent.putExtra(Constants.USER_PHONE_TAG, users.get(position).getPhone());
            selectedUserIntent.putExtra(Constants.USER_CELL_TAG, users.get(position).getCell());
            context.startActivity(selectedUserIntent);
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userFullNameTextView)
        TextView userFullNameTV;
        @BindView(R.id.userThumbnailImageView)
        ImageView userThumbnailIV;
        @BindView(R.id.briefInfoItemRoot)
        View container;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
