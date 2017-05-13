package com.wwb.library.title;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;

import com.dd.library.R;
import com.wwb.library.Library;
import com.wwb.library.config.DDLibraryConfig;
import com.wwb.library.drawable.DDDrawable;
import com.wwb.library.utils.DDCollectionUtil;
import com.wwb.library.utils.DDViewUtil;

import java.util.ArrayList;
import java.util.List;


public class DDTitleSimple extends LinearLayout implements OnClickListener
{
	public View mView;
	public DDTitleItem mTitleLeft;
	public DDTitleItem mTitleMiddle;
	public LinearLayout mLlLeft;
	public LinearLayout mLlMiddle;
	public LinearLayout mLlRight;

	private int mWidthRight;
	private int mWidthMiddle;
	private int mWidthLeft;

	private int mBackgroundItemResource;
	private DDTitleSimpleListener mListener;
	private DDLibraryConfig mConfig = Library.getInstance().getConfig();

	private List<DDTitleItem> mListRightItem = new ArrayList<DDTitleItem>();

	public void setmListener(DDTitleSimpleListener listener)
	{
		this.mListener = listener;
	}

	public List<DDTitleItem> getmListRightItem()
	{
		return mListRightItem;
	}

	public DDTitleSimple(Context context)
	{
		this(context, null);
	}

	public DDTitleSimple(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	private void init()
	{
		mView = LayoutInflater.from(getContext()).inflate(R.layout.title_simple, null);
		this.addView(mView, DDViewUtil.getLayoutParamsLinearLayoutMM());

		mTitleLeft = (DDTitleItem) findViewById(R.id.title_left);
		mTitleMiddle = (DDTitleItem) findViewById(R.id.title_middle);
		mLlLeft = (LinearLayout) findViewById(R.id.ll_left);
		mLlMiddle = (LinearLayout) findViewById(R.id.ll_middle);
		mLlRight = (LinearLayout) findViewById(R.id.ll_right);

		mTitleLeft.setOnClickListener(this);
		mTitleMiddle.setOnClickListener(this);
		setDefaultConfig();
		notifyItemBackgroundChanged();
		addLayoutListener();
	}

	private void addLayoutListener()
	{
		mLlLeft.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener()
		{
			@Override
			public void onGlobalLayout()
			{
				int newWidth = mLlLeft.getWidth();
				if (mWidthLeft != newWidth)
				{
					mWidthLeft = newWidth;
					changeViewOnLayoutChange();
				}
			}
		});
		mLlMiddle.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener()
		{
			@Override
			public void onGlobalLayout()
			{
				int newWidth = mLlMiddle.getWidth();
				if (mWidthMiddle != newWidth)
				{
					mWidthMiddle = newWidth;
					changeViewOnLayoutChange();
				}
			}
		});
		mLlRight.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener()
		{
			@Override
			public void onGlobalLayout()
			{
				int newWidth = mLlRight.getWidth();
				if (mWidthRight != newWidth)
				{
					mWidthRight = newWidth;
					changeViewOnLayoutChange();
				}
			}
		});
	}

	protected void changeViewOnLayoutChange()
	{
		int width = 0;
		if (mWidthRight >= mWidthLeft)
		{
			width = mWidthRight;
		} else
		{
			width = mWidthLeft;
		}

		int maxMiddleWidth = ((DDViewUtil.getViewWidth(this) / 2) - width) * 2;

		if (mWidthMiddle > maxMiddleWidth)
		{
			DDViewUtil.setViewWidth(mLlMiddle, maxMiddleWidth);
		} else
		{

		}
	}

	private void setDefaultConfig()
	{
		setBackgroundColor(mConfig.getTitleColor());
		setHeightTitle(mConfig.getTitleHeight());
	}

	public DDTitleSimple setHeightTitle(int height)
	{
		DDViewUtil.setViewHeight(mView, height);
		return this;
	}

	private Drawable getBackgroundItem()
	{
		DDDrawable none = new DDDrawable();
		none.color(mConfig.getTitleColor());

		DDDrawable pressed = new DDDrawable();
		pressed.color(mConfig.getTitleColorPressed());

		return DDDrawable.getStateListDrawable(none, null, null, pressed);
	}

	public DDTitleSimple initRightItem(int count)
	{
		mLlRight.removeAllViews();
		mListRightItem.clear();
		if (count <= 0)
		{
			return this;
		}

		for (int i = 0; i < count; i++)
		{
			addItemRight();
		}
		notifyItemBackgroundChanged();
		return this;
	}

	public DDTitleItem addItemRight()
	{
		DDTitleItem item = new DDTitleItem(getContext());
		item.setOnClickListener(this);
		if (mBackgroundItemResource != 0)
		{
			DDViewUtil.setBackgroundResource(item, mBackgroundItemResource);
		} else
		{
			DDViewUtil.setBackgroundDrawable(item, getBackgroundItem());
		}
		LayoutParams params = DDViewUtil.getLayoutParamsLinearLayoutWM();
		mLlRight.addView(item, params);
		mListRightItem.add(item);
		return item;
	}

	public void setmBackgroundItemResource(int resId)
	{
		this.mBackgroundItemResource = resId;
		notifyItemBackgroundChanged();
	}

	private void notifyItemBackgroundChanged()
	{
		if (mBackgroundItemResource != 0)
		{
			DDViewUtil.setBackgroundResource(mTitleLeft, mBackgroundItemResource);
			for (DDTitleItem item : mListRightItem)
			{
				DDViewUtil.setBackgroundResource(item, mBackgroundItemResource);
			}
		} else
		{
			setDefaultItemBackground();
		}
	}

	private void setDefaultItemBackground()
	{
		DDViewUtil.setBackgroundDrawable(mTitleLeft, getBackgroundItem());
		for (DDTitleItem item : mListRightItem)
		{
			DDViewUtil.setBackgroundDrawable(item, getBackgroundItem());
		}
	}

	public DDTitleItem getItemRight(int index)
	{
		return DDCollectionUtil.get(mListRightItem, index);
	}

	@Override
	public void onClick(View v)
	{
		if (v == mTitleLeft)
		{
			clickLeft(v);
		} else if (v == mTitleMiddle)
		{
			clickMiddle(v);
		} else
		{
			clickRight(v);
		}

	}

	private void clickLeft(View v)
	{
		if (mListener != null)
		{
			mListener.onCLickLeft_DDTitleSimple(mTitleLeft);
		}
	}

	private void clickMiddle(View v)
	{
		if (mListener != null)
		{
			mListener.onCLickMiddle_DDTitleSimple(mTitleMiddle);
		}
	}

	private void clickRight(View v)
	{
		if (mListener != null)
		{
			int index = mListRightItem.indexOf(v);
			if (index >= 0)
			{
				mListener.onCLickRight_DDTitleSimple(mListRightItem.get(index), index);
			}
		}
	}

	// ---------------------setCustomView
	public DDTitleSimple setCustomViewLeft(View view)
	{
		mLlLeft.removeAllViews();
		if (view != null)
		{
			mLlLeft.addView(view);
		}
		return this;
	}

	public DDTitleSimple setCustomViewLeft(int resId)
	{
		View view = LayoutInflater.from(getContext()).inflate(resId, null);
		setCustomViewLeft(view);
		return this;
	}

	public DDTitleSimple setCustomViewMiddle(View view)
	{
		mLlMiddle.removeAllViews();
		if (view != null)
		{
			mLlMiddle.addView(view);
		}
		return this;
	}

	public DDTitleSimple setCustomViewMiddle(int resId)
	{
		View view = LayoutInflater.from(getContext()).inflate(resId, null);
		setCustomViewMiddle(view);
		return this;
	}

	public DDTitleSimple setCustomViewRight(View view)
	{
		mLlRight.removeAllViews();
		if (view != null)
		{
			mLlRight.addView(view);
		}
		return this;
	}

	public DDTitleSimple setCustomViewRight(int resId)
	{
		View view = LayoutInflater.from(getContext()).inflate(resId, null);
		setCustomViewRight(view);
		return this;
	}

	// friendly method
	public DDTitleSimple setMiddleTextTop(String text)
	{
		mTitleMiddle.setTextTop(text);
		return this;
	}

	public DDTitleSimple setMiddleTextBot(String text)
	{
		mTitleMiddle.setTextBot(text);
		return this;
	}

	public DDTitleSimple setMiddleImageLeft(int resId)
	{
		mTitleMiddle.setImageLeft(resId);
		return this;
	}

	public DDTitleSimple setMiddleImageRight(int resId)
	{
		mTitleMiddle.setImageRight(resId);
		return this;
	}

	public DDTitleSimple setMiddleBackgroundText(int resId)
	{
		mTitleMiddle.setBackgroundText(resId);
		return this;
	}

	public DDTitleSimple setLeftTextTop(String text)
	{
		mTitleLeft.setTextTop(text);
		return this;
	}

	public DDTitleSimple setLeftTextBot(String text)
	{
		mTitleLeft.setTextBot(text);
		return this;
	}

	public DDTitleSimple setLeftImageLeft(int resId)
	{
		mTitleLeft.setImageLeft(resId);
		return this;
	}

	public DDTitleSimple setLeftImageRight(int resId)
	{
		mTitleLeft.setImageRight(resId);
		return this;
	}

	public DDTitleSimple setLeftBackgroundText(int resId)
	{
		mTitleLeft.setBackgroundText(resId);
		return this;
	}

	public interface DDTitleSimpleListener
	{
		public void onCLickLeft_DDTitleSimple(DDTitleItem v);

		public void onCLickMiddle_DDTitleSimple(DDTitleItem v);

		public void onCLickRight_DDTitleSimple(DDTitleItem v, int index);
	}

}
