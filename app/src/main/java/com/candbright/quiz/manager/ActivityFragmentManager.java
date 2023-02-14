package com.candbright.quiz.manager;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.candbright.base.activity.BaseActivity;
import com.candbright.base.fragment.BaseFragment;

/**
 * <p>created by wyh in 2021/11/16</p>
 */
public class ActivityFragmentManager<Activity extends BaseActivity> {
    private static final String TAG = ActivityFragmentManager.class.getSimpleName();
    private Activity activity;
    private FragmentManager fragmentManager;
    private int containerId;

    public ActivityFragmentManager(Activity activity, int containerId) {
        this.activity = activity;
        fragmentManager = activity.getSupportFragmentManager();
        this.containerId = containerId;
    }

    public void addFragment(BaseFragment fragment) {
        addFragment(containerId, fragment);
    }

    public void addFragment(int containerViewId, BaseFragment fragment) {
        if (null == fragment) {
            return;
        }
        if (null == fragmentManager) {
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerViewId, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    public void addOrReplaceFragment(BaseFragment fragment) {
        addOrReplaceFragment(containerId, fragment);
    }

    public void addOrReplaceFragment(int containerViewId, BaseFragment fragment) {
        if (null == fragment) {
            return;
        }
        if (null == fragmentManager) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment, fragment.getClass().getName());
        transaction.commit();
    }
}
