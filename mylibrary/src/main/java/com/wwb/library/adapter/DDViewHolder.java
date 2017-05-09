package com.wwb.library.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class DDViewHolder
{
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context context;

    public View getConvertView()
    {
        return mConvertView;
    }

    public DDViewHolder(Context context, ViewGroup parent, int layoutId,
                        int position)
    {
        this.mViews = new SparseArray<View>();
        this.mPosition = position;
        this.context = context;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId,
                parent, false);
        this.mConvertView.setTag(this);
    }

    public static DDViewHolder get(Context context, View convertView,
                                   ViewGroup parent, int layoutId, int position)
    {
        if (null == convertView)
        {
            return new DDViewHolder(context, parent, layoutId, position);
        } else
        {
            DDViewHolder holder = (DDViewHolder) convertView.getTag();
            holder.mPosition = position;

            return holder;
        }
    }

    /**
     * @param viewId
     * @return T
     * @author
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);

        if (null == view)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    /**
     * @param viewId
     * @param text
     * @return ViewHolder
     * @author
     */
    public DDViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);

        return this;
    }

    public DDViewHolder setImageResouce(int viewId, int resId)
    {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);

        return this;
    }

    public DDViewHolder setImageBitmap(int viewId, Bitmap bitmap)
    {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);

        return this;
    }

//	public ViewHolder setImageUrl(int viewId, int DefaultResId, String url) {
//		ImageView iv = getView(viewId);
//		Glide.with(context).load(url).error(R.drawable.empty_photo)
//				.placeholder(DefaultResId).into(iv);
//
//		return this;
//	}

    public DDViewHolder setImageDrawable(int viewId, Drawable drawable)
    {
        ImageView iv = getView(viewId);
        iv.setImageDrawable(drawable);
        return this;
    }

    public DDViewHolder setLinearLayoutBackground(int viewId, int color)
    {
        LinearLayout ll = getView(viewId);
        ll.setBackgroundColor(color);
        return this;
    }

}
