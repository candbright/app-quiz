package com.candbright.quiz.manager;

import com.candbright.quiz.app.GlobalApp;

/**
 * <p>created by wyh in 2021/12/16</p>
 */
public class LocalFileManager {
    public static String getCacheDirAbsolutePath() {
        return GlobalApp.getInstance().getExternalCacheDir().getAbsolutePath();
    }
}
