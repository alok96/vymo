package com.zef.vymo;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zef.vymo.R;
import com.zef.vymo.base.BaseActivity;
import com.zef.vymo.data.RepoPullData;
import com.zef.vymo.ui.RepoPullFragment;
import com.zef.vymo.viewmodel.RepoPullViewModel;
import com.zef.vymo.viewmodel.RepoPullViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.et_owner_name)
    EditText ownerName;
    @BindView(R.id.et_repo_name)
    EditText repoName;
    @BindView(R.id.btn_submit)
    Button submitBtn;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  ownerName.setText("JakeWharton");
      //  repoName.setText("ViewPagerIndicator");
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = ownerName.getText().toString();
                final String repo = repoName.getText().toString();

                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(repo)) {
                    if (savedInstanceState == null) {
                        RepoPullFragment repoPullFragment = new RepoPullFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("user", user);
                        bundle.putString("repo", repo);
                        repoPullFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_pull_container, repoPullFragment)
                                .addToBackStack(null).commit();
                    }

                }
            }
        });
    }
}
