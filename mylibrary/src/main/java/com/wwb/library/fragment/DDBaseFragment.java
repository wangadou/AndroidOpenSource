package com.wwb.library.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wwb.library.activity.DDBaseActivity;
import com.wwb.library.listener.DDDispatchKeyEventListener;
import com.wwb.library.listener.DDDispatchTouchEventListener;
import com.wwb.library.listener.DDVisibilityStateListener;
import com.wwb.library.manager.DDFragmentManager;
import com.wwb.library.utils.DDCollectionUtil;
import com.wwb.library.utils.DDViewUtil;

import java.util.List;

/**
 * Created by wwb on 2017/5/11.
 */

public abstract class DDBaseFragment extends Fragment implements OnClickListener, DDDispatchTouchEventListener, DDDispatchKeyEventListener
{

    private DDFragmentManager fragmentManager;
    private DDVisibilityStateListener visibilityStateListener;


    public DDBaseFragment()
    {
        ensureArgumentsNotNull();
    }

    /**
     * @Description 判断内部bundle是否为空
     */
    private void ensureArgumentsNotNull()
    {
        if (getArguments() == null)
        {
            super.setArguments(new Bundle());
        }
    }

    @Override
    public void setArguments(Bundle args)
    {
        ensureArgumentsNotNull();
        if (args != null)
        {
            getArguments().putAll(args);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View contentView = onCreateContentView(inflater, container, savedInstanceState);
        if (container == null)
        {
            int layoutId = onCreateContentView();
            if (layoutId != 0)
            {
                View layoutView = inflater.inflate(layoutId, container, false);
                contentView = addTitleView(layoutView);
            }
        }
        return contentView;
    }

    /**
     * 为contentView添加titleView
     *
     * @param
     * @return
     */
    private View addTitleView(View layoutView)
    {
        View finalView = layoutView;
        View titleView = onCreateTitleView();
        if (titleView != null)
        {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(titleView, createTitleViewLayoutParams());
            ll.addView(finalView, createContentViewLayoutParams());
            finalView = ll;
        }
        return finalView;
    }

    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return null;
    }

    /**
     * 此方法用于被重写返回fragment布局id
     *
     * @return
     */
    protected abstract int onCreateContentView();


    private View createTitleView()
    {
        View view = onCreateTitleView();
        if (view == null)
        {
            int resId = onCreateTitleViewResId();
            if (resId != 0)
            {
                view = LayoutInflater.from(getActivity()).inflate(resId, null);
            }
        }
        return view;
    }


    protected View onCreateTitleView()
    {
        return null;
    }

    protected int onCreateTitleViewResId()
    {
        return 0;
    }

    protected LinearLayout.LayoutParams createTitleViewLayoutParams()
    {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    protected LinearLayout.LayoutParams createContentViewLayoutParams()
    {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public DDFragmentManager getDDFragmentManager()
    {
        if (fragmentManager == null)
        {
            fragmentManager = new DDFragmentManager(getChildFragmentManager());
        }
        return fragmentManager;
    }

    public void setVisibilityStateListener(DDVisibilityStateListener visibilityStateListener)
    {
        this.visibilityStateListener = visibilityStateListener;
    }


    public DDBaseActivity getSDBaseActivity()
    {
        DDBaseActivity ddBaseActivity = null;
        if (getActivity() instanceof DDBaseActivity)
        {
            ddBaseActivity = (DDBaseActivity) getActivity();
        }
        return ddBaseActivity;
    }

    public boolean isEmpty(CharSequence content)
    {
        return TextUtils.isEmpty(content);
    }

    public boolean isEmpty(List<?> list)
    {
        return DDCollectionUtil.isEmpty(list);
    }

    public void hideFragmentView()
    {
        DDViewUtil.hide(getView());
        notifyVisibleState();
    }

    public void showFragmentView()
    {
        DDViewUtil.show(getView());
        notifyVisibleState();
    }

    public void invisibleFragmentView()
    {
        DDViewUtil.invisible(getView());
        notifyVisibleState();
    }

    //监听
    public void notifyVisibleState()
    {
        View view = getView();
        if (view != null && visibilityStateListener != null)
        {
            int visibility = view.getVisibility();
            if (visibility == View.VISIBLE)
            {
                visibilityStateListener.onVisible(view);
            } else if (visibility == View.INVISIBLE)
            {
                visibilityStateListener.onInvisible(view);
            } else if (visibility == View.GONE)
            {
                visibilityStateListener.onGone(view);
            }
        }
    }


    @Override
    public void onClick(View v)
    {

    }

    public boolean onBackPressed()
    {
        return false;
    }

    @Override
    public boolean dispatchKeyEventActivity(KeyEvent event)
    {
        switch (event.getAction())
        {
            case KeyEvent.ACTION_DOWN:
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                {
                    return onBackPressed();
                }
                break;

            default:
                break;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEventActivity(MotionEvent ev)
    {
        return false;
    }
}
