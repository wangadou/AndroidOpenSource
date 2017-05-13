package com.wwb.library;

import android.app.Application;

import com.dd.library.R;
import com.wwb.library.config.DDLibraryConfig;

/**
 * Created by wwb on 2017/5/9.
 */

public class Library
{
    private static Library instance;
    private Application application;

    private DDLibraryConfig config;

    public Application getApplication()
    {
        return application;
    }

    public void init(Application application)
    {
        this.application = application;
        initDefaultConfig();
    }

    private Library()
    {

    }

    public DDLibraryConfig getConfig()
    {
        return config;
    }

    public void setConfig(DDLibraryConfig config)
    {
        this.config = config;
    }


    private void initDefaultConfig()
    {
        int main_color = application.getResources().getColor(R.color.main_color);
        int main_color_press = application.getResources().getColor(R.color.main_color_press);

        int bg_title_bar = application.getResources().getColor(R.color.bg_title_bar);
        int bg_title_bar_pressed = application.getResources().getColor(R.color.bg_title_bar_pressed);

        int stroke = application.getResources().getColor(R.color.stroke);
        int gray_press = application.getResources().getColor(R.color.gray_press);

        int text_title_bar = application.getResources().getColor(R.color.text_title_bar);

        int height_title_bar = application.getResources().getDimensionPixelOffset(R.dimen.height_title_bar);
        int corner = application.getResources().getDimensionPixelOffset(R.dimen.corner);
        int width_stroke = application.getResources().getDimensionPixelOffset(R.dimen.width_stroke);

        config.setMainColor(main_color);
        config.setMainColorPress(main_color_press);
        config.setGrayPressColor(gray_press);
        config.setTitleColor(bg_title_bar);
        config.setTitleColorPressed(bg_title_bar_pressed);
        config.setTitleTextColor(text_title_bar);
        config.setTitleHeight(height_title_bar);
        config.setStrokeColor(stroke);
        config.setStrokeWidth(width_stroke);
        config.setCornerRadius(corner);
    }


    public static Library getInstance()
    {
        if (instance == null)
        {
            instance = new Library();
        }
        return instance;
    }
}
