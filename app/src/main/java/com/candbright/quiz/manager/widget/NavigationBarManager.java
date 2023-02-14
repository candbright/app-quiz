package com.candbright.quiz.manager.widget;

import android.view.View;

import com.candbright.quiz.databinding.NavigationBarBinding;

/**
 * <p>created by wyh in 2021/12/15</p>
 */
public class NavigationBarManager {
    private NavigationBarBinding navigationBar;

    public NavigationBarManager(NavigationBarBinding navigationBar) {
        this.navigationBar = navigationBar;
    }

    public NavigationBarManager setLeftOnClickListener(View.OnClickListener listener) {
        navigationBar.buttonLeft.setOnClickListener(listener);
        return this;
    }

    public NavigationBarManager setRightOnClickListener(View.OnClickListener listener) {
        navigationBar.buttonRight.setOnClickListener(listener);
        return this;
    }

    public NavigationBarManager setTitle(String title) {
        navigationBar.tvTitle.setText(title);
        return this;
    }

    public NavigationBarManager setTitle(int stringRes) {
        navigationBar.tvTitle.setText(stringRes);
        return this;
    }

    public NavigationBarManager setLeftImageSrc(int src) {
        navigationBar.buttonLeft.setImageResource(src);
        return this;
    }

    public NavigationBarManager setRightImageSrc(int src) {
        navigationBar.buttonRight.setImageResource(src);
        return this;
    }
}
