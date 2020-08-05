package com.zef.vymo.ui;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BulletSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zef.vymo.R;
import com.zef.vymo.data.RepoPullData;
import com.zef.vymo.viewmodel.RepoPullViewModel;

public class RepoPullListAdapter extends RecyclerView.Adapter<RepoPullListAdapter.RepoViewHolder> {
    private final List<RepoPullData> data = new ArrayList<>();

    RepoPullListAdapter(RepoPullViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pull_title)
        TextView tvPullTitle;
        @BindView(R.id.tv_pull_no)
        TextView tvPullNumber;
        @BindView(R.id.tv_pull_status)
        TextView tvPullStatus;
        @BindView(R.id.tv_pull_created)
        TextView tvPullCreated;

        private String pull_title = "Pull Title: ";
        private String pull_num = "PR Number: ";
        private String pull_status = "Pull Status: ";
        private String pull_created = "Created: ";
        private Context context;

        RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(RepoPullData row) {
            if (!TextUtils.isEmpty(row.getTitle())) {
                tvPullTitle.setText(pull_title + row.getTitle());
            } else tvPullTitle.setText(R.string.empty_title);

            if (!TextUtils.isEmpty(row.getNumber())) {
                tvPullNumber.setText(pull_num + " #" + row.getNumber());
            } else tvPullNumber.setText(R.string.emplty_num);

            if (!TextUtils.isEmpty(row.getStatus())) {
                tvPullStatus.setText(pull_status + " " + row.getStatus());
            } else tvPullStatus.setText(R.string.empty_status);

            if (!TextUtils.isEmpty(row.getCreated_at())) {
                tvPullCreated.setText(pull_created + row.getCreated_at());
            } else tvPullCreated.setText(R.string.empty_created);
        }
    }
}
