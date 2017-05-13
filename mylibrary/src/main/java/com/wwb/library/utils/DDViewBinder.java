package com.wwb.library.utils;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DDViewBinder
{


    public static void setRatingBar(RatingBar ratingBar, float rating)
    {
        ratingBar.setRating(rating);
    }

    public static void setTextView(TextView textView, CharSequence content, CharSequence emptyTip)
    {
        if (!TextUtils.isEmpty(content))
        {
            textView.setText(content);
        } else
        {
            if (!TextUtils.isEmpty(emptyTip))
            {
                textView.setText(emptyTip);
            } else
            {
                textView.setText("");
            }
        }
    }

    public static void setTextView(TextView textView, CharSequence content)
    {
        setTextView(textView, content, null);
    }

    public static void setTextView(EditText editText, CharSequence content)
    {
        if (!TextUtils.isEmpty(content))
        {
            editText.setText(content);
        } else
        {
            editText.setText("");
        }
    }

    public static void setTextViewHtml(TextView textView, String contentHtml)
    {
        setTextViewHtml(textView, contentHtml, null);
    }

    public static void setTextViewHtml(TextView textView, String contentHtml, String emptyTip)
    {
        CharSequence content = contentHtml;
        if (!TextUtils.isEmpty(contentHtml))
        {
            content = Html.fromHtml(contentHtml);
        }
        setTextView(textView, content, emptyTip);
    }

    public static void setTextViewsVisibility(TextView textView, CharSequence content)
    {
        if (TextUtils.isEmpty(content))
        {
            DDViewUtil.hide(textView);
        } else
        {
            textView.setText(content);
            DDViewUtil.show(textView);
        }
    }

    public static void setImageViewsVisibility(ImageView imageView, int resId)
    {
        if (resId <= 0)
        {
            DDViewUtil.hide(imageView);
        } else
        {
            imageView.setImageResource(resId);
            DDViewUtil.show(imageView);
        }
    }

    public static boolean setViewsVisibility(View view, boolean visible)
    {
        if (visible)
        {
            DDViewUtil.show(view);
        } else
        {
            DDViewUtil.hide(view);
        }
        return visible;
    }

    public static boolean setViewsVisibility(View view, int visible)
    {
        if (visible == 1)
        {
            DDViewUtil.show(view);
            return true;
        } else
        {
            DDViewUtil.hide(view);
            return false;
        }
    }


    public static void setImageViewResource(ImageView imageView, int resId, boolean setZeroResId)
    {
        if (resId == 0)
        {
            if (setZeroResId)
            {
                imageView.setImageResource(resId);
            } else
            {

            }
        } else
        {
            imageView.setImageResource(resId);
        }
    }


}
