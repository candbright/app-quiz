package com.candbright.quiz.ui.activity;

import com.candbright.base.activity.BaseExitActivity;
import com.candbright.quiz.R;
import com.candbright.quiz.databinding.ActivityHomeBinding;
import com.candbright.quiz.databinding.NavigationBottomBarBinding;
import com.candbright.quiz.manager.ActivityFragmentManager;
import com.candbright.quiz.manager.widget.NavigationBottomBarManager;
import com.candbright.quiz.ui.fragment.QuestionBySubjectFragment;
import com.candbright.quiz.ui.fragment.SelectQuestionSubjectFragment;

/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class HomeActivity extends BaseExitActivity<ActivityHomeBinding> {
    private NavigationBottomBarBinding navigationBarBottom;
    private ActivityFragmentManager fragmentManager;

    protected String getExitMessage() {
        return "再次点击后退出APP";
    }

    @Override
    protected void initViewBinding() {
        navigationBarBottom = NavigationBottomBarBinding.bind(rootBinding.getRoot());
    }

    @Override
    protected void initManager() {
        fragmentManager = new ActivityFragmentManager<>(this, R.id.fragment_container);
        fragmentManager.addOrReplaceFragment(new SelectQuestionSubjectFragment());
        NavigationBottomBarManager navigationBottomBarManager = new NavigationBottomBarManager(navigationBarBottom);
        navigationBottomBarManager.setFirstButtonClickListener(v -> {
            fragmentManager.addOrReplaceFragment(new SelectQuestionSubjectFragment());
        });
        navigationBottomBarManager.setSecondButtonClickListener(v -> {
            fragmentManager.addOrReplaceFragment(new QuestionBySubjectFragment("subject_language"));
        });
        navigationBottomBarManager.setThirdClickListener(v -> {

        });
    }

    @Override
    public void onBackPressed() {
        if (!fragmentManager.popBackStackImmediate()) {
            super.onBackPressed();
        }
    }
}
