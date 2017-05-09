package com.wwb.library;

import android.app.Application;

/**
 * Created by wwb on 2017/5/9.
 */

public class Library
{
    private static Library instance;
    private Application application;


    public Application getApplication()
    {
        return application;
    }

    public void init(Application application)
    {
        this.application = application;
    }

    private Library()
    {

    }

    public static Library getInstance()
    {
        if (instance == null)
        {
            instance = new Library();
        }
        return instance;
    }
}
