package com.adou.cloudmusic.app;

import android.app.Application;
import android.content.Context;

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
    }

    public static Context getContext()
    {
        return sContext;
    }
}
