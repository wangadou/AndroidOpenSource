package com.wwb.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sunday.eventbus.SDBaseEvent;
import com.sunday.eventbus.SDEventManager;
import com.sunday.eventbus.SDEventObserver;
import com.wwb.library.manager.DDActivityManager;

/**
 * Created by adou on 2017/5/9.
 */

public class DDBaseActivity extends AppCompatActivity implements View.OnClickListener, SDEventObserver
{
    protected DDBaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivity = this;
        SDEventManager.register(this);
        DDActivityManager.getInstance().onCreate(this);
        afterOnCreater(savedInstanceState);
    }

    private void afterOnCreater(Bundle savedInstanceState)
    {
        int layoutId = onCreateContentView();
        if (layoutId != 0)
        {
            setContentView(layoutId);
        }
        init(savedInstanceState);
    }

    /**
     * 返回布局activity布局id，基类调用的顺序：onCreateContentView()->setContentView()->init()
     *
     * @return
     */
    protected int onCreateContentView()
    {
        return 0;
    }

    protected void init(Bundle savedInstanceState)
    {
    }

    @Override
    protected void onResume()
    {
        DDActivityManager.getInstance().onResume(this);
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        SDEventManager.unregister(this);
        DDActivityManager.getInstance().onDestroy(this);
        super.onDestroy();
    }

    @Override
    public void finish()
    {
        DDActivityManager.getInstance().onDestroy(this);
        super.finish();
    }

    @Override
    public void onClick(View view)
    {

    }

    @Override
    public void onEvent(SDBaseEvent sdBaseEvent)
    {

    }

    @Override
    public void onEventMainThread(SDBaseEvent sdBaseEvent)
    {

    }

    @Override
    public void onEventBackgroundThread(SDBaseEvent sdBaseEvent)
    {

    }

    @Override
    public void onEventAsync(SDBaseEvent sdBaseEvent)
    {

    }
}
