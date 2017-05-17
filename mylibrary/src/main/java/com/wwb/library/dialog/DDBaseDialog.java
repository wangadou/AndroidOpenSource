package com.wwb.library.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.dd.library.R;
import com.sunday.eventbus.SDBaseEvent;
import com.sunday.eventbus.SDEventManager;
import com.sunday.eventbus.SDEventObserver;
import com.wwb.library.Library;
import com.wwb.library.config.DDLibraryConfig;
import com.wwb.library.drawable.DDDrawable;
import com.wwb.library.drawable.DDDrawableManager;
import com.wwb.library.manager.DDActivityManager;
import com.wwb.library.manager.HandlerManager;
import com.wwb.library.utils.DDViewUtil;

/**
 * Created by adou on 2017/5/13.
 */

public class DDBaseDialog extends Dialog implements SDEventObserver, OnDismissListener, View.OnClickListener
{
    public static final int DEFAULT_PADDING_LEFT_RIGHT = DDViewUtil.dp2px(20);
    public static final int DEFAULT_PADDING_TOP_BOTTOM = DDViewUtil.dp2px(10);

    private View contentView;
    protected LinearLayout linearLayoutRoot;
    protected DDLibraryConfig config = Library.getInstance().getConfig();
    public DDDrawableManager drawableManager;
    protected boolean dismissAfterClick = true;

    public DDBaseDialog()
    {
        this(DDActivityManager.getInstance().getLastActivity());
    }

    public DDBaseDialog(@NonNull Activity activity)
    {
        this(activity, R.style.dialogBaseBlur);
    }

    public DDBaseDialog(@NonNull Activity activity, @StyleRes int themeResId)
    {
        super(activity, themeResId);
        setOwnerActivity(activity);
        baseInit();
    }

    private void baseInit()
    {
        if (isOpenEventBus())
        {
            SDEventManager.register(this);
        }
        linearLayoutRoot = new LinearLayout(getContext());
        linearLayoutRoot.setBackgroundColor(Color.parseColor("#00000000"));
        linearLayoutRoot.setGravity(Gravity.CENTER);
        this.setOnDismissListener(this);
        initDrawable();
        setCanceledOnTouchOutside(false);
    }

    private void initDrawable()
    {
        drawableManager = new DDDrawableManager();
    }

    /**
     * @date 2017/5/13
     * @author wwb
     * @Description 需要注册是时候重写下，默认不开启
     */
    protected boolean isOpenEventBus()
    {
        return false;
    }


    /**
     * @date 2017/5/14
     * @author wwb
     * @Description 设置gravity
     */
    public DDBaseDialog setGravity(int gravity)
    {
        getWindow().setGravity(gravity);
        return this;
    }

    public void showTop()
    {
        showTop(true);
    }


    /**
     * 显示在顶部
     *
     * @param anim 是否需要动画
     */
    public void showTop(boolean anim)
    {

        setGravity(Gravity.TOP);
        if (anim)
        {
            setAnimations(R.style.anim_top_top);
        }
        show();
    }


    public void showBottom()
    {
        showBottom(true);
    }

    /**
     * 显示在底部
     *
     * @param anim 是否需要动画
     */
    public void showBottom(boolean anim)
    {
        setGravity(Gravity.BOTTOM);
        if (anim)
        {
            setAnimations(R.style.anim_bottom_bottom);
        }
        show();
    }

    public void showCenter()
    {
        setGravity(Gravity.CENTER);
        show();
    }

    /**
     * 设置窗口的显示和隐藏动画
     *
     * @param resId
     */
    public void setAnimations(int resId)
    {
        getWindow().setWindowAnimations(resId);
    }

    /**
     * 是否点击按钮后自动关闭窗口
     *
     * @return
     */
    public boolean isDismissAfterClick()
    {
        return dismissAfterClick;
    }

    /**
     * 设置是否点击按钮后自动关闭窗口,默认true(是)
     *
     * @param dismissAfterClick
     * @return
     */
    public DDBaseDialog setDismissAfterClick(boolean dismissAfterClick)
    {
        this.dismissAfterClick = dismissAfterClick;
        return this;
    }

    @Override
    public void show()
    {
        if (getOwnerActivity() != null && !getOwnerActivity().isFinishing())
        {
            super.show();
        }
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        stopDismissRunnable();
        SDEventManager.unregister(this);
    }

    public void destroy()
    {
        if (isShowing())
        {
            dismiss();
        } else
        {
            onStop();
        }
        setOwnerActivity(null);
    }

// -----------------------padding

    public DDBaseDialog paddingTop(int top)
    {
        linearLayoutRoot.setPadding(linearLayoutRoot.getPaddingLeft(), top, linearLayoutRoot.getPaddingRight(), linearLayoutRoot.getPaddingBottom());
        return this;
    }

    public DDBaseDialog paddingBottom(int bottom)
    {
        linearLayoutRoot.setPadding(linearLayoutRoot.getPaddingLeft(), linearLayoutRoot.getPaddingTop(), linearLayoutRoot.getPaddingRight(), bottom);
        return this;
    }

    public DDBaseDialog paddingLeft(int left)
    {
        linearLayoutRoot.setPadding(left, linearLayoutRoot.getPaddingTop(), linearLayoutRoot.getPaddingRight(), linearLayoutRoot.getPaddingBottom());
        return this;
    }

    public DDBaseDialog paddingRight(int right)
    {
        linearLayoutRoot.setPadding(linearLayoutRoot.getPaddingLeft(), linearLayoutRoot.getPaddingTop(), right, linearLayoutRoot.getPaddingBottom());
        return this;
    }

    public DDBaseDialog paddings(int paddings)
    {
        linearLayoutRoot.setPadding(paddings, paddings, paddings, paddings);
        return this;
    }

    /**
     * 设置窗口上下左右的边距
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    public DDBaseDialog padding(int left, int top, int right, int bottom)
    {
        linearLayoutRoot.setPadding(left, top, right, bottom);
        return this;
    }

    private DDBaseDialog setDialogView(View view, ViewGroup.LayoutParams params)
    {
        contentView = view;
        wrapperView(contentView);
        if (params == null)
        {
            params = new ViewGroup.LayoutParams(DDViewUtil.getScreenWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        padding(DEFAULT_PADDING_LEFT_RIGHT, DEFAULT_PADDING_TOP_BOTTOM, DEFAULT_PADDING_LEFT_RIGHT, DEFAULT_PADDING_TOP_BOTTOM);
        super.setContentView(linearLayoutRoot, params);
        return this;
    }

    private void wrapperView(View view)
    {
        linearLayoutRoot.removeAllViews();
        linearLayoutRoot.addView(view, DDViewUtil.getLayoutParamsLinearLayoutMM());
    }

    protected void dismissAfterClick()
    {
        if (dismissAfterClick)
        {
            dismiss();
        }
    }

    /**
     * 设置高度
     *
     * @param width
     * @return
     */
    public DDBaseDialog setWidth(int width)
    {
        DDViewUtil.setViewWidth(linearLayoutRoot, width);
        return this;
    }

    /**
     * 设置宽度
     *
     * @param height
     * @return
     */
    public DDBaseDialog setHeight(int height)
    {
        DDViewUtil.setViewHeight(linearLayoutRoot, height);
        return this;
    }

    /**
     * 设置全屏
     *
     * @return
     */
    public DDBaseDialog setFullScreen()
    {
        paddings(0);
        setWidth(DDViewUtil.getScreenWidth()).setHeight(DDViewUtil.getScreenHeight() - DDViewUtil.getStatusBarHeight());
        return this;
    }

    // ------------------------setContentView

    @Override
    public void setContentView(int layoutResID)
    {
        View view = LayoutInflater.from(getContext()).inflate(layoutResID, null);
        this.setContentView(view, null);
    }

    public void setContentView(int layoutResID, ViewGroup.LayoutParams params)
    {
        View view = LayoutInflater.from(getContext()).inflate(layoutResID, null);
        this.setContentView(view, params);
    }

    @Override
    public void setContentView(View view)
    {
        this.setContentView(view, null);
    }

    @Override
    public void setContentView(View view, LayoutParams params)
    {
        setDialogView(view, params);
    }

    public View getContentView()
    {
        return contentView;
    }

    public void startDismissRunnable(long delay)
    {
        stopDismissRunnable();
        HandlerManager.getMainHandler().postDelayed(dismissRunnable, delay);
    }

    public void stopDismissRunnable()
    {
        HandlerManager.getMainHandler().removeCallbacks(dismissRunnable);
    }

    private Runnable dismissRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            dismiss();
        }
    };


    /**
     * 边框：top，right 圆角：bottomLeft
     *
     * @return
     */
    public Drawable getBackgroundBottomLeft()
    {
        DDDrawable drawableCancel = new DDDrawable();
        drawableCancel.strokeColor(config.getStrokeColor()).strokeWidth(0, config.getStrokeWidth(), config.getStrokeWidth(), 0)
                .cornerBottomLeft(config.getCornerRadius());

        DDDrawable drawableCancelPressed = new DDDrawable();
        drawableCancelPressed.strokeColor(config.getStrokeColor()).color(config.getGrayPressColor())
                .strokeWidth(0, config.getStrokeWidth(), config.getStrokeWidth(), 0).cornerBottomLeft(config.getCornerRadius());

        return DDDrawable.getStateListDrawable(drawableCancel, null, null, drawableCancelPressed);
    }

    /**
     * 边框：top 圆角：bottomRight
     *
     * @return
     */
    public Drawable getBackgroundBottomRight()
    {
        DDDrawable drawableConfirm = new DDDrawable();
        drawableConfirm.strokeColor(config.getStrokeColor()).strokeWidth(0, config.getStrokeWidth(), 0, 0)
                .cornerBottomRight(config.getCornerRadius());

        DDDrawable drawableConfirmPressed = new DDDrawable();
        drawableConfirmPressed.strokeColor(config.getStrokeColor()).color(config.getGrayPressColor())
                .strokeWidth(0, config.getStrokeWidth(), 0, 0).cornerBottomRight(config.getCornerRadius());

        return DDDrawable.getStateListDrawable(drawableConfirm, null, null, drawableConfirmPressed);
    }

    /**
     * 边框：top 圆角：bottomLeft，bottomRight
     *
     * @return
     */
    public Drawable getBackgroundBottomSingle()
    {
        DDDrawable drawableConfirm = new DDDrawable();
        drawableConfirm.strokeColor(config.getStrokeColor()).strokeWidth(0, config.getStrokeWidth(), 0, 0)
                .corner(0, 0, config.getCornerRadius(), config.getCornerRadius());

        DDDrawable drawableConfirmPressed = new DDDrawable();
        drawableConfirmPressed.strokeColor(config.getStrokeColor()).color(config.getGrayPressColor())
                .strokeWidth(0, config.getStrokeWidth(), 0, 0).corner(0, 0, config.getCornerRadius(), config.getCornerRadius());
        return DDDrawable.getStateListDrawable(drawableConfirm, null, null, drawableConfirmPressed);
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

    @Override
    public void onDismiss(DialogInterface dialogInterface)
    {

    }

    @Override
    public void onClick(View view)
    {

    }
}
