package com.candbright.quiz.dao.helper;

import android.content.Context;

import com.candbright.quiz.dao.DaoMaster;
import com.candbright.quiz.dao.DaoSession;
import com.candbright.quiz.dao.QuestionDao;
import com.candbright.quiz.model.data.Question;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * <p>created by wyh in 2021/12/13</p>
 */
public class QuestionDaoHelper {
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private QuestionDao mDao;
    private static QuestionDaoHelper mDaoHelper;

    private QuestionDaoHelper(Context context) {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), "QUESTION.db", null);
        mDaoMaster = new DaoMaster(mDevOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
        mDao = mDaoSession.getQuestionDao();
    }

    public static QuestionDaoHelper getInstance(Context context) {
        if (mDaoHelper == null) {
            synchronized (QuestionDaoHelper.class) {
                if (mDaoHelper == null) {
                    mDaoHelper = new QuestionDaoHelper(context);
                }
            }
        }
        return mDaoHelper;
    }

    /**
     * add
     */
    public long insert(Question data) {
        return mDao.insert(data);
    }

    /**
     * add
     */
    public void insert(List<Question> data) {
        for (Question datum : data) {
            mDao.insert(datum);
        }
    }

    public void insertOrReplace(Question data) {
        mDao.insertOrReplace(data);
    }

    /**
     * delete all
     */
    public void deleteAll() {
        mDaoSession.deleteAll(Question.class);
    }

    /**
     * update
     */
    public void update(Question data) {
        Question old = mDao.queryBuilder().where(QuestionDao.Properties.Id.eq(data.getId())).build().unique();
        if (old != null) {
            mDao.update(data);
        }
    }

    public List<Question> searchById(String id) {
        QueryBuilder<Question> songQueryBuilder = mDao.queryBuilder();
        List<Question> data = songQueryBuilder.where(QuestionDao.Properties.Id.eq(id)).list();
        return data;
    }

    public List<Question> searchBySubject(String subject) {
        QueryBuilder<Question> songQueryBuilder = mDao.queryBuilder();
        List<Question> data = songQueryBuilder.where(QuestionDao.Properties.Subject.eq(subject)).list();
        return data;
    }

    public List<Question> searchAll() {
        List<Question> data = mDao.queryBuilder().list();
        return data;
    }

}
