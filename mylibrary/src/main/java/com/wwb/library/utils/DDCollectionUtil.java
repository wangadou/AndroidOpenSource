package com.wwb.library.utils;

import com.wwb.library.listener.DDIterateListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wwb on 2017/5/11.
 * 集合工具类
 */

public class DDCollectionUtil
{

    public static boolean isEmpty(List<?> list)
    {
        if (list != null && !list.isEmpty())
        {
            return false;
        } else
        {
            return true;
        }
    }

    /**
     * @date 2017/5/11
     * @author wwb
     * @Description index是否合法
     */
    public static <T> boolean isIndexLegal(List<T> list, int index)
    {
        if (!isEmpty(list) && index >= 0 && index < list.size())
        {
            return true;
        } else
        {
            return false;
        }
    }


    public static <T> T get(List<T> list, int index)
    {
        T t = null;
        if (isIndexLegal(list, index))
        {
            t = list.get(index);
        }
        return t;
    }

    /**
     * @date 2017/5/11
     * @author wwb
     * @Description 迭代(返回出i, item, 迭代器)
     */
    public static <T> boolean iterate(Iterable<T> iterable, DDIterateListener<T> listener)
    {
        if (iterable != null && listener != null)
        {
            Iterator<T> it = iterable.iterator();

            int i = 0;
            T item = null;
            while (it.hasNext())
            {
                item = it.next();
                if (listener.next(i, item, it))
                {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    /**
     * @date 2017/5/11
     * @author wwb
     * @Description 遍历(返回出i, item)
     */
    public static <T> boolean foreach(List<T> list, DDIterateListener<T> listener)
    {
        if (!isEmpty(list) && listener != null)
        {
            int size = list.size();
            for (int i = 0; i < size; i++)
            {
                if (listener.next(i, list.get(i), null))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @date 2017/5/11
     * @author wwb
     * @Description 反着遍历(返回出i, item)
     */
    public static <T> boolean foreachReverse(List<T> list, DDIterateListener<T> listener)
    {
        if (!isEmpty(list) && listener != null)
        {
            int size = list.size();
            for (int i = size - 1; i >= 0; i--)
            {
                if (listener.next(i, list.get(i), null))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @date 2017/5/11
     * @author wwb
     * @Description 获取倒数index的item
     */
    public static <T> T getLast(List<T> list, int index)
    {
        T t = null;
        if (isIndexLegal(list, index))
        {
            index = list.size() - 1 - index;
            t = list.get(index);
        }
        return t;
    }

    /**
     * @date 2017/5/11
     * @author wwb
     * @Description list<T> 转成 T[]数组
     */
    public static <T> T[] toArray(List<T> list)
    {
        T[] arr = null;
        if (!isEmpty(list))
        {
            T item = list.get(0);
            if (item != null)
            {
                Class<?> clazzItem = item.getClass();
                arr = (T[]) Array.newInstance(clazzItem, list.size());
                list.toArray(arr);
            }
        }
        return arr;
    }

    /**
     * @date 2017/5/11
     * @author wwb
     * @Description 切割出新集合
     */
    public static <T> List<T> subList(List<T> list, int start, int end)
    {
        List<T> listReturn = null;
        if (end >= start && isIndexLegal(list, start) && isIndexLegal(list, end))
        {
            listReturn = new ArrayList<T>();
            for (int i = start; i <= end; i++)
            {
                T t = list.get(i);
                listReturn.add(t);
            }
        }
        return listReturn;
    }

    /**
     * @date 2017/5/11
     * @author wwb
     * @Description 切割出start到末尾的list
     */
    public static <T> List<T> subList(List<T> list, int start)
    {
        List<T> listReturn = null;
        if (isIndexLegal(list, start))
        {
            listReturn = new ArrayList<T>();
            for (int i = start; i < list.size(); i++)
            {
                T t = list.get(i);
                listReturn.add(t);
            }
        }
        return listReturn;
    }


    /**
     * @date 2017/5/11
     * @author wwb
     * @Description 移除start到end中间的是item
     */
    public static <T> void removeList(List<T> list, int start, int end)
    {
        if (end >= start && isIndexLegal(list, start) && isIndexLegal(list, end))
        {
            Iterator<T> it = list.iterator();
            int i = 0;
            while (it.hasNext())
            {
                if (i >= start)
                {
                    if (i <= end)
                    {
                        it.next();
                        it.remove();
                    } else
                    {
                        break;
                    }
                }
                i++;
            }
        }
    }
}
