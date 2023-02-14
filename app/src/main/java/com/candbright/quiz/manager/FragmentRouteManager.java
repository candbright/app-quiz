package com.candbright.quiz.manager;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.candbright.base.fragment.BaseFragment;

/**
 * <p>created by wyh in 2021/11/16</p>
 */
public class FragmentRouteManager<Fragment extends BaseFragment> {

    private static final String TAG = "FragmentRouteManager";
    public static final int ANIMATION_NONE = -1;
    public static final int FRAGMENT_TRANSACT_ANIM_VERTICAL = 1;
    public static final int FRAGMENT_TRANSACT_ANIM_HORIZONTAL = 2;

    private final Fragment fragment;
    private final FragmentManager fragmentManager;

    public FragmentRouteManager(@NonNull Fragment fragment) {
        this.fragment = fragment;
        fragmentManager = fragment.getFragmentManager();
    }

    @SuppressLint("WrongConstant")
    public void goToSubFragment(BaseFragment newFragment) {
        goToSubFragment(newFragment, FRAGMENT_TRANSACT_ANIM_HORIZONTAL);
    }

    public void goToSubFragment(BaseFragment newFragment, int animationRes) {
        this.addSubFragment(newFragment, animationRes);
    }

    public void addSubFragment(BaseFragment newFragment, int animRes) {

        if (null == newFragment) {
            Log.e(TAG, "addSubFragment error, can't add a null fragment");
            return;
        }

        if (null == fragmentManager) {
            Log.e(TAG, "addSubFragment error, fragment manager is null");
            return;
        }

        if (-1 == fragment.getContainerId()) {
            Log.e(TAG, "addSubFragment error, error container id ");
            return;
        }

        newFragment.setContainerId(fragment.getContainerId());

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(fragment.getContainerId(), newFragment, newFragment.getClass().getName());
        transaction.hide(fragment);
        transaction.addToBackStack(newFragment.getClass().getName());
        transaction.commit();
    }

    public static void addFragmentToContainer(FragmentManager fragmentManager, int containerViewId, BaseFragment fragment) {

        if (null == fragment) {
            Log.e(TAG, "(addFragment) --- can not add an null fragment");
            return;
        }

        if (null == fragmentManager) {
            Log.e(TAG, "(addFragment) --- fragment manager is null");
            return;
        }

        fragment.setContainerId(containerViewId);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerViewId, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    public void backToLastFragment() {
        if (null != fragmentManager && fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            fragment.getActivity().finish();
        }
    }
}
