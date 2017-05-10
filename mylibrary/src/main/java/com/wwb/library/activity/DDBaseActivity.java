package com.wwb.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sunday.eventbus.SDBaseEvent;
import com.sunday.eventbus.SDEventManager;
import com.sunday.eventbus.SDEventObserver;

/**
 * Created by adou on 2017/5/9.
 */

public class DDBaseActivity extends AppCompatActivity implements View.OnClickListener,SDEventObserver {
    protected DDBaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        SDEventManager.register(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onEvent(SDBaseEvent sdBaseEvent) {

    }

    @Override
    public void onEventMainThread(SDBaseEvent sdBaseEvent) {

    }

    @Override
    public void onEventBackgroundThread(SDBaseEvent sdBaseEvent) {

    }

    @Override
    public void onEventAsync(SDBaseEvent sdBaseEvent) {

    }
}
