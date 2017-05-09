package com.adou.cloudmusic.app;

import android.app.Application;
import android.content.Context;

import com.wwb.library.Library;

/**
 * Created by wwb on 2017/5/9.
 */

public class App extends Application
{
    private static Context sContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        sContext = getApplicationContext();
        init();
    }

    private void init()
    {
        Library.getInstance().init(this);
    }

    public static Context getContext()
    {
        return sContext;
    }
}
