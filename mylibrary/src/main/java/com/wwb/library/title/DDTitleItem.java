package com.wwb.library.title;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.library.R;
import com.wwb.library.Library;
import com.wwb.library.utils.DDViewBinder;
import com.wwb.library.utils.DDViewUtil;


public class DDTitleItem extends LinearLayout
{
    public View mView;
    public ImageView mIvLeft;
    public ImageView mIvRight;
    public TextView mTvTop;
    public TextView mTvBot;
    public LinearLayout mLlText;
    private Drawable mBackgroundDrawable;
    private OnClickListener mListenerOnClick;

    public DDTitleItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public DDTitleItem(Context context)
    {
        this(context, null);
    }

    private void init()
    {
        this.setGravity(Gravity.CENTER);
        this.setLayoutParams(DDViewUtil.getLayoutParamsLinearLayoutWM());
        mView = LayoutInflater.from(getContext()).inflate(R.layout.title_item, null);
        this.addView(mView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        this.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        mIvLeft = (ImageView) findViewById(R.id.title_item_iv_left);
        mIvRight = (ImageView) findViewById(R.id.title_item_iv_right);

        mLlText = (LinearLayout) findViewById(R.id.title_item_ll_text);
        mTvTop = (TextView) findViewById(R.id.title_item_tv_top);
        mTvBot = (TextView) findViewById(R.id.title_item_tv_bot);

        setDefaultConfig();

        setAllViewsVisibility(View.GONE);
    }

    private void setDefaultConfig()
    {
        int titleTextColor = Library.getInstance().getConfig().getTitleTextColor();
        mTvTop.setTextColor(titleTextColor);
        mTvBot.setTextColor(titleTextColor);
    }

    public void setAllViewsVisibility(int visibility)
    {
        mIvLeft.setVisibility(visibility);
        mIvRight.setVisibility(visibility);

        mLlText.setVisibility(visibility);
        mTvTop.setVisibility(visibility);
        mTvBot.setVisibility(visibility);
        dealClickListener();
    }

    public DDTitleItem setTextTop(String text)
    {
        DDViewBinder.setTextViewsVisibility(mTvTop, text);
        dealClickListener();
        return this;
    }

    public DDTitleItem setTextBot(String text)
    {
        DDViewBinder.setTextViewsVisibility(mTvBot, text);
        dealClickListener();
        return this;
    }

    public DDTitleItem setBackgroundText(int resId)
    {
        mLlText.setBackgroundResource(resId);
        return this;
    }

    public DDTitleItem setImageLeft(int resId)
    {
        if (resId == 0)
        {
            mIvLeft.setVisibility(View.GONE);
        } else
        {
            mIvLeft.setVisibility(View.VISIBLE);
            mIvLeft.setImageResource(resId);
        }
        dealClickListener();
        return this;
    }

    public DDTitleItem setImageRight(int resId)
    {
        if (resId == 0)
        {
            mIvRight.setVisibility(View.GONE);
        } else
        {
            mIvRight.setVisibility(View.VISIBLE);
            mIvRight.setImageResource(resId);
        }
        dealClickListener();
        return this;
    }

    public TextView getmTvTop()
    {
        return mTvTop;
    }

    public void setmTvTop(TextView mTvTop)
    {
        this.mTvTop = mTvTop;
    }

    public boolean hasViewVisible()
    {
        if (mIvLeft.getVisibility() == View.VISIBLE || mTvTop.getVisibility() == View.VISIBLE || mTvBot.getVisibility() == View.VISIBLE
                || mIvRight.getVisibility() == View.VISIBLE)
        {
            if (mTvTop.getVisibility() == View.VISIBLE || mTvBot.getVisibility() == View.VISIBLE)
            {
                mLlText.setVisibility(View.VISIBLE);
            } else
            {
                mLlText.setVisibility(View.GONE);
            }
            return true;
        } else
        {
            return false;
        }
    }

    private void dealClickListener()
    {
        if (hasViewVisible())
        {
            setBackgroundDrawableSaved();
            super.setOnClickListener(mListenerOnClick);
        } else
        {
            setBackgroundTransparent();
            super.setOnClickListener(null);
        }
    }

    private void setBackgroundDrawableSaved()
    {
        int top = getPaddingTop();
        int bottom = getPaddingBottom();
        int left = getPaddingLeft();
        int right = getPaddingRight();
        super.setBackgroundDrawable(mBackgroundDrawable);
        setPadding(left, top, right, bottom);
    }

    private void setBackgroundTransparent()
    {
        int top = getPaddingTop();
        int bottom = getPaddingBottom();
        int left = getPaddingLeft();
        int right = getPaddingRight();
        super.setBackgroundDrawable(null);
        setPadding(left, top, right, bottom);
    }

    @Override
    public void setOnClickListener(OnClickListener l)
    {
        this.mListenerOnClick = l;
        super.setOnClickListener(l);
        dealClickListener();
    }

    @Override
    @Deprecated
    public void setBackgroundDrawable(Drawable background)
    {
        this.mBackgroundDrawable = background;
        super.setBackgroundDrawable(background);
    }

}
