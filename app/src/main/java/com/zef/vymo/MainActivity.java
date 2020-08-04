package com.zef.vymo;

import android.os.Bundle;

import com.zef.vymo.R;
import com.zef.vymo.base.BaseActivity;
import com.zef.vymo.ui.RepoPullFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new RepoPullFragment()).commit();
    }
}
