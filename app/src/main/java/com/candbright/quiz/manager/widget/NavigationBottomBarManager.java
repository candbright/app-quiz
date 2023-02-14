package com.candbright.quiz.manager.widget;

import android.view.View;

import com.candbright.quiz.R;
import com.candbright.quiz.databinding.NavigationBottomBarBinding;

/**
 * <p>created by wyh in 2021/12/15</p>
 */
public class NavigationBottomBarManager {
    private NavigationBottomBarBinding navigationBottomBar;
    int navigatorId;

    public NavigationBottomBarManager(NavigationBottomBarBinding navigationBottomBar) {
        this.navigationBottomBar = navigationBottomBar;
        init();
        matchBottomTv();
    }

    public void init() {
        navigationBottomBar.imageFirst.setImageResource(R.drawable.navigation_star_songs);
        navigationBottomBar.tvFirst.setText(R.string.navigation_bottom_first);
        navigationBottomBar.imageSecond.setImageResource(R.drawable.navigation_star_square);
        navigationBottomBar.tvSecond.setText(R.string.navigation_bottom_second);
        navigationBottomBar.imageThird.setImageResource(R.drawable.navigation_star_ufo);
        navigationBottomBar.tvThird.setText(R.string.navigation_bottom_third);
    }

    public void matchBottomTv() {
        navigationBottomBar.tvFirst.setVisibility(View.GONE);
        navigationBottomBar.tvSecond.setVisibility(View.GONE);
        navigationBottomBar.tvThird.setVisibility(View.GONE);
        switch (navigatorId) {
            case 0:
                navigationBottomBar.tvFirst.setVisibility(View.VISIBLE);
                break;
            case 1:
                navigationBottomBar.tvSecond.setVisibility(View.VISIBLE);
                break;
            case 2:
                navigationBottomBar.tvThird.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setFirstButtonClickListener(View.OnClickListener listener) {
        navigationBottomBar.buttonFirst.setOnClickListener(v -> {
            navigatorId = 0;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setSecondButtonClickListener(View.OnClickListener listener) {
        navigationBottomBar.buttonSecond.setOnClickListener(v -> {
            navigatorId = 1;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setThirdClickListener(View.OnClickListener listener) {
        navigationBottomBar.buttonThird.setOnClickListener(v -> {
            navigatorId = 2;
            matchBottomTv();
            listener.onClick(v);
        });
    }
}
