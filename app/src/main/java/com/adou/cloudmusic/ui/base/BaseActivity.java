package com.adou.cloudmusic.ui.base;

import android.os.Bundle;

import com.wwb.library.activity.DDBaseActivity;

/**
 * Created by adou on 2017/5/9.
 */

public abstract class BaseActivity extends DDBaseActivity {

    @Override
    protected int onCreateContentView() {
        return createContentView();
    }

    @Override
    protected void baseInit(Bundle savedInstanceState) {
        init(savedInstanceState);
    }

    @Override
    protected boolean isOpenEventBus() {
        return openEventBus();
    }

    protected abstract int createContentView();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract boolean openEventBus();
}
