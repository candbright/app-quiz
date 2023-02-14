package com.candbright.quiz.util;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;

import com.candbright.quiz.manager.StatusBarManager;

/**
 * <p>created by wyh in 2021/11/27</p>
 * 在Activity的onCreate中加入AndroidBug5497Workaround.assistActivity(this)即可使RecyclerView中的Item自动跟随键盘上移而不遮挡标题栏。
 */
public class AndroidBug5497Workaround {

    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    private View mContentView;//我们设置的contentView
    private FrameLayout.LayoutParams mFrameLayoutParams;//我们设置的contentView的layoutParams

    //    private boolean isNavigationShowing = false;没有用到这个
    //    private boolean isFullscreenMode = false;没有用到这个
    //    private int barNavigationHeight = 0;虚拟导航栏高度，没用
    //    private int barNavigationWidth = 0;虚拟导航栏宽度，没用

    private int barStatusHeight = 0;//状态栏高度

    private int lastUsableHeight = 0;//上一次的可用高度
    private int lastUsableWidth = 0;//上一次的可用宽度

    private AndroidBug5497Workaround(final Activity activity) {
        //1.获取 状态栏 高度，获取 导航栏 高度、宽度
        barStatusHeight = StatusBarManager.getStatusBarHeight(activity);
        //barNavigationHeight = getNavigationBarHeight(activity);
        //barNavigationWidth = getNavigationBarWidth(activity);
        //2.找到Activity的最外层布局控件，它其实是一个DecorView,它所用的控件就是FrameLayout
        final FrameLayout content = activity.findViewById(android.R.id.content);
        //3.获取到setContentView放进去的View
        mContentView = content.getChildAt(0);
        //4.拿到我们设置的View的布局参数，主要是调整该参数来实现软键盘弹出上移
        mFrameLayoutParams = (FrameLayout.LayoutParams) mContentView.getLayoutParams();
        //5.给我们设置的View添加布局变动的监听，来实现布局动作（虚拟导航栏的弹出收起也会触发该监听！）
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            //软键盘弹出、系统导航栏隐藏显示均会触发这里
            int heightRoot = content.getRootView().getHeight();//包含虚拟按键的高度（如果有的话）
            int heightDecor = content.getHeight();//不含虚拟按键的高度，貌似不包含状态栏高度

            int usableHeight = computeUsableHeight();//我们setContentView设置的view的可用高度

            if (usableHeight != lastUsableHeight) {
                lastUsableHeight = usableHeight;//防止重复变动

                int heightDifference = heightDecor - usableHeight;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && activity.isInMultiWindowMode()) {//如果是分屏模式
                    if (heightDifference > 0) {//分屏模式，只要变动了就人为弹出键盘，因为分屏可能该Activity是在手机屏幕的上方，弹出输入法只是遮盖了一丁点~如果不合适，需要你自己适配了！
                        setHeight(heightDecor - heightDifference); //这里不能加状态栏高度哟~
                    } else {
                        setHeight(FrameLayout.LayoutParams.MATCH_PARENT);//还原默认高度，不能用计算的值，因为虚拟导航栏显示或者隐藏的时候也会改变高度
                    }
                } else {
                    if (heightDifference > (heightDecor / 4)) {//高度变动超过decor的四分之一则认为是软键盘弹出事件，为什么不用屏幕高度呢？开始以为这样在分屏模式下也可以监听，但是实测不行。
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            setHeight(heightDecor - heightDifference + barStatusHeight);//这里为什么要添加状态栏高度？
                        } else {
                            setHeight(heightDecor - heightDifference);//这里不添加状态栏高度？不懂为什么，原版如此，就先这样吧。遇到再说~
                        }
                    } else {
                        setHeight(FrameLayout.LayoutParams.MATCH_PARENT);//还原默认高度，不能用计算的值，因为虚拟导航栏显示或者隐藏的时候也会改变高度
                    }
                }
            }
        });
    }

    private void setHeight(int height) {
        if (mFrameLayoutParams.height != height) {//不必要的更新就不要了
            mFrameLayoutParams.height = height;
            mContentView.requestLayout();//触发布局更新
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mContentView.getWindowVisibleDisplayFrame(r);
        // 全屏模式下：直接返回r.bottom，r.top其实是状态栏的高度
        return (r.bottom - r.top);
    }

    private int computeUsableWidth() {
        Rect r = new Rect();
        mContentView.getWindowVisibleDisplayFrame(r);
        // 全屏模式下：直接返回r.bottom，r.top其实是状态栏的高度//横屏就是宽度
        return (r.right - r.left);
    }
}