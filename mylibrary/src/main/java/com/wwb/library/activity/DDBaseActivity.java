package com.wwb.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.sunday.eventbus.SDBaseEvent;
import com.sunday.eventbus.SDEventManager;
import com.sunday.eventbus.SDEventObserver;
import com.wwb.library.manager.DDActivityManager;
import com.wwb.library.manager.DDFragmentManager;
import com.wwb.library.utils.DDCollectionUtil;
import com.wwb.library.utils.DDViewUtil;

import java.util.List;

/**
 * Created by adou on 2017/5/9.
 */

public class DDBaseActivity extends AppCompatActivity implements View.OnClickListener, SDEventObserver
{
    private FrameLayout flRootLayout;
    protected DDBaseActivity mActivity;
    boolean isOpenEventBus;
    private DDFragmentManager fragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivity = this;
        flRootLayout = (FrameLayout) findViewById(android.R.id.content);
        DDActivityManager.getInstance().onCreate(this);
        afterOnCreater(savedInstanceState);
        if (isOpenEventBus)
        {
            SDEventManager.register(this);
        }
    }

    private void afterOnCreater(Bundle savedInstanceState)
    {
        int layoutId = onCreateContentView();
        if (layoutId != 0)
        {
            setContentView(layoutId);
        }
        baseInit(savedInstanceState);
    }

    public void setOpenEventBus(boolean openEventBus)
    {
        isOpenEventBus = openEventBus;
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

    protected void baseInit(Bundle savedInstanceState)
    {
    }


    protected <V extends View> V find(int id)
    {
        View view = findViewById(id);
        return (V) view;
    }


    protected boolean isEmpty(CharSequence content)
    {
        return TextUtils.isEmpty(content);
    }

    public boolean isEmpty(List<?> list)
    {
        return DDCollectionUtil.isEmpty(list);
    }


    public DDFragmentManager getSDFragmentManager()
    {
        if (fragmentManager == null)
        {
            fragmentManager = new DDFragmentManager(getSupportFragmentManager());
        }
        return fragmentManager;
    }

    public void addView(View view)
    {
        DDViewUtil.addView(flRootLayout, view);
    }

    public void addView(View view, FrameLayout.LayoutParams params)
    {
        DDViewUtil.addView(flRootLayout, view, params);
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
