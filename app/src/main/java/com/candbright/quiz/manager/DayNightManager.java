package com.candbright.quiz.manager;

import android.app.Activity;

import androidx.appcompat.app.AppCompatDelegate;

import com.candbright.quiz.R;

/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class DayNightManager {
    private Activity activity;

    public DayNightManager(Activity activity) {
        this.activity = activity;
    }

    //切换日夜模式
    public void setDayNightMode(boolean isDayMode) {
        if (isDayMode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        activity.getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        activity.recreate();

    }
}
