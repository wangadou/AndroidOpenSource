package com.wwb.library.manager;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by adou on 2017/5/10.
 */

public class DDActivityManager
{
    private static Stack<Activity> mStackActivity;
    private static DDActivityManager mInstance;

    private DDActivityManager()
    {
        mStackActivity = new Stack<Activity>();
    }

    public static DDActivityManager getInstance()
    {
        if (mInstance == null)
        {
            synchronized (DDActivityManager.class)
            {
                if (mInstance == null)
                {
                    mInstance = new DDActivityManager();
                }
            }
        }
        return mInstance;
    }

    // ----------------------------activity life method

    public void onCreate(Activity activity)
    {
        addActivity(activity);
    }

    public void onResume(Activity activity)
    {
        addActivity(activity);
    }

    /**
     * finish()和onDestroy()都要调用
     *
     * @param activity
     */
    public void onDestroy(Activity activity)
    {
        removeActivity(activity);
    }

    private void addActivity(Activity activity)
    {
        if (!mStackActivity.contains(activity))
        {
            mStackActivity.add(activity);
        }
    }

    private void removeActivity(Activity activity)
    {
        if (activity != null)
        {
            mStackActivity.remove(activity);
        }
    }

    public Activity getLastActivity()
    {
        Activity activity = null;
        try
        {
            activity = mStackActivity.lastElement();
        } catch (Exception e)
        {
        }
        return activity;
    }

    public boolean isLastActivity(Activity activity)
    {
        if (activity != null)
        {
            return getLastActivity() == activity;
        } else
        {
            return false;
        }
    }

    public boolean isEmpty()
    {
        return mStackActivity.isEmpty();
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls)
    {
        Iterator<Activity> it = mStackActivity.iterator();
        while (it.hasNext())
        {
            Activity act = it.next();
            if (act.getClass() == cls)
            {
                it.remove();
                act.finish();
            }
        }
    }

    public void finishAllClassActivityExcept(Activity activity)
    {
        Iterator<Activity> it = mStackActivity.iterator();
        while (it.hasNext())
        {
            Activity act = it.next();
            if (act.getClass() == activity.getClass() && act != activity)
            {
                it.remove();
                act.finish();
            }
        }
    }

    public void finishAllActivity()
    {
        Iterator<Activity> it = mStackActivity.iterator();
        while (it.hasNext())
        {
            Activity act = it.next();
            it.remove();
            act.finish();
        }
    }

    public void finishAllActivityExcept(Class<?> cls)
    {
        Iterator<Activity> it = mStackActivity.iterator();
        while (it.hasNext())
        {
            Activity act = it.next();
            if (act.getClass() != cls)
            {
                it.remove();
                act.finish();
            }
        }
    }

    public void finishAllActivityExcept(Activity activity)
    {
        Iterator<Activity> it = mStackActivity.iterator();
        while (it.hasNext())
        {
            Activity act = it.next();
            if (act != activity)
            {
                it.remove();
                act.finish();
            }
        }
    }
}
