package com.candbright.quiz.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.candbright.quiz.dao.helper.QuestionDaoHelper;
import com.candbright.quiz.dao.helper.QuestionSubjectDaoHelper;
import com.candbright.quiz.model.data.Question;
import com.candbright.quiz.model.data.QuestionSubject;
import com.candbright.quiz.util.L;
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
        //TODO 应该删除此处的测试数据
        Boolean isRead = (Boolean) Utility.getShareFileData(this, "isRead", false);
        if (!isRead) {
            String questionSubjectStr = Utility.getJson(this, "question_subject.json");
            List<QuestionSubject> questionSubjectData = Utility.jsonToList(questionSubjectStr, QuestionSubject.class);
            if (questionSubjectData != null && questionSubjectData.size() > 0) {
                QuestionSubjectDaoHelper daoHelper = QuestionSubjectDaoHelper.getInstance();
                daoHelper.insert(questionSubjectData);
            }
            String questionStr = Utility.getJson(this, "question.json");
            List<Question> questionData = Utility.jsonToList(questionStr, Question.class);
            if (questionData != null && questionData.size() > 0) {
                QuestionDaoHelper daoHelper = QuestionDaoHelper.getInstance();
                daoHelper.insert(questionData);
            }
            Utility.setShareFileData(this, "isRead", true);
        }
    }


    public static String getResString(int resId) {
        String string = "";

        if (null == singleton) {
            L.e(TAG, "getResString failed, because application is null.");
            return string;
        }

        Context context = singleton.getApplicationContext();
        if (null == context) {
            L.e(TAG, "getResString failed, because context is null.");
            return string;
        }

        Resources resources = context.getResources();
        if (null == resources) {
            L.e(TAG, "getResString failed, because resources is null.");
            return string;
        }

        try {
            string = resources.getString(resId);
        } catch (Exception e) {
            L.e(TAG, "getResString failed, because Resources.getString() method cause an exception.");
        }

        return string;
    }
}
