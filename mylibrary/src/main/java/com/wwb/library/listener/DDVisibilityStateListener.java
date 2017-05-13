package com.wwb.library.listener;

import android.view.View;

/**
 * Created by wwb on 2017/5/13.
 */

public interface DDVisibilityStateListener
{
    void onVisible(View view);

    void onGone(View view);

    void onInvisible(View view);
}
