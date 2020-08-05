package com.zef.vymo.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;

import com.zef.vymo.R;
import com.zef.vymo.base.BaseFragment;
import com.zef.vymo.viewmodel.RepoPullViewModel;
import com.zef.vymo.viewmodel.RepoPullViewModelFactory;

import static android.content.ContentValues.TAG;

public class RepoPullFragment extends BaseFragment {

    @BindView(R.id.rv_pull_items)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_progress_bar)
    View loadingView;

    @Inject
    RepoPullViewModelFactory viewModelFactory;
    private RepoPullViewModel viewModel;
    @Override
    protected int layoutRes() {
        return R.layout.fragment_pullview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle b = getArguments();
        String user = b.getString("user");
        String repo = b.getString("repo");
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoPullViewModel.class);
        viewModel.fetchRepos(user, repo);
        listView.setAdapter(new RepoPullListAdapter(viewModel, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        observableViewModel();
    }

    private void observableViewModel() {
        viewModel.getUser().observe(this, user -> {
        });

        viewModel.getRepos().observe(this, repos -> {
            if (repos != null) listView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText(R.string.error_pull_response);
            } else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
