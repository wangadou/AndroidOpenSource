package com.wwb.library.listener;

import java.util.Iterator;

/**
 * Created by wwb on 2017/5/11.
 */

public interface DDIterateListener<T>
{
    /**
     * 返回true，结束遍历
     *
     * @param i
     * @param item
     * @param it
     * @return
     */
    boolean next(int i, T item, Iterator<T> it);
}
