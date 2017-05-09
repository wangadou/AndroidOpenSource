package com.wwb.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @author wwb
 * @date 2015-8-5 10:39:05
 */
public abstract class CommonAdapter<T> extends BaseAdapter
{
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mlayoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId)
    {
        this.mContext = context;
        this.mDatas = datas;
        this.mlayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    /**
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public T getItem(int position)
    {
        return mDatas.get(position);
    }

    /**
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * @see android.widget.Adapter#getView(int, View, ViewGroup)
     */
//	@Override
//	public abstract View getView(int position, View convertView, ViewGroup parent);
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        DDViewHolder holder = DDViewHolder.get(mContext, convertView, parent, mlayoutId, position);

        convert(holder, getItem(position));

        return holder.getConvertView();
    }

    public abstract void convert(DDViewHolder holder, T t);

    /**
     * 更新adapter的数据集合，并刷新adapter
     *
     * @param listModel
     */
    public void updateData(List<T> listModel)
    {
        setData(listModel);
        notifyDataSetChanged();
    }

    /**
     * 给adapter设置数据
     *
     * @param listModel
     */
    public void setData(List<T> listModel)
    {
        if (listModel != null)
        {
            this.mDatas = listModel;
        } else
        {
            this.mDatas = new ArrayList<T>();
        }
//        resetSelection();
    }
}
