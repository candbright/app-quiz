package com.candbright.quiz.ui.home;

import com.candbright.base.activity.BaseExitActivity;
import com.candbright.quiz.R;
import com.candbright.quiz.databinding.ActivityHomeBinding;
import com.candbright.quiz.databinding.NavigationBottomBarBinding;
import com.candbright.quiz.manager.ActivityFragmentManager;
import com.candbright.quiz.manager.widget.NavigationBottomBarManager;

/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class HomeActivity extends BaseExitActivity<ActivityHomeBinding> {

    private static final String TAG = "<MainActivity>";

    private NavigationBottomBarBinding navigationBarBottom;
    private ActivityFragmentManager fragmentManager;
    private NavigationBottomBarManager navigationBottomBarManager;

    protected String getExitMessage() {
        return "再次点击后退出APP";
    }

    @Override
    protected void initViewBinding() {
        navigationBarBottom = NavigationBottomBarBinding.bind(rootBinding.getRoot());

    }

    @Override
    protected void initManager() {
        fragmentManager = new ActivityFragmentManager(this, R.id.fragment_container);
        fragmentManager.addOrReplaceFragment(new QuestionBankFragment());
        navigationBottomBarManager = new NavigationBottomBarManager(navigationBarBottom);
        navigationBottomBarManager.setFirstButtonClickListener(v -> {
            fragmentManager.addOrReplaceFragment(new QuestionBankFragment());
        });
        navigationBottomBarManager.setSecondButtonClickListener(v -> {
            fragmentManager.addOrReplaceFragment(new QuestionBankFragment());
        });
        navigationBottomBarManager.setThirdClickListener(v -> {

        });
    }
}
