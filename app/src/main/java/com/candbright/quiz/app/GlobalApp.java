package com.candbright.quiz.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.candbright.quiz.dao.helper.QuestionSubjectDaoHelper;
import com.candbright.quiz.model.data.QuestionSubject;
import com.candbright.quiz.util.Utility;

import java.util.List;

/**
 * <p>created by wyh in 2021/12/10</p>
 */
public class GlobalApp extends Application {
    private static final String TAG = GlobalApp.class.getSimpleName();
    private static GlobalApp singleton;

    public static GlobalApp getInstance() {
        return singleton;
    }

    public GlobalApp() {
        super();
        singleton = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDao();
    }

    private void initDao() {
        Boolean isRead = (Boolean) Utility.getShareFileData(this, "isRead", false);
        if (!isRead) {
            String jsonStr = Utility.getJson(this, "question_subject.json");
            List<QuestionSubject> data = Utility.jsonToList(jsonStr, QuestionSubject.class);
            if (data != null && data.size() > 0) {
                QuestionSubjectDaoHelper daoHelper = QuestionSubjectDaoHelper.getInstance(this);
                daoHelper.insert(data);
            }
        }
    }


    public static String getResString(int resId) {
        String string = "";

        if (null == singleton) {
            Log.e(TAG, "getResString failed, because application is null.");
            return string;
        }

        Context context = singleton.getApplicationContext();
        if (null == context) {
            Log.e(TAG, "getResString failed, because context is null.");
            return string;
        }

        Resources resources = context.getResources();
        if (null == resources) {
            Log.e(TAG, "getResString failed, because resources is null.");
            return string;
        }

        try {
            string = resources.getString(resId);
        } catch (Exception e) {
            Log.e(TAG, "getResString failed, because Resources.getString() method cause an exception.");
        }

        return string;
    }
}
