package com.candbright.quiz.dao.helper;

import android.content.Context;

import com.candbright.quiz.dao.DaoMaster;
import com.candbright.quiz.dao.DaoSession;
import com.candbright.quiz.dao.QuestionBankDao;
import com.candbright.quiz.model.data.QuestionBank;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * <p>created by wyh in 2021/12/13</p>
 */
public class QuestionBankDaoHelper {
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private QuestionBankDao mDao;
    private static QuestionBankDaoHelper mDaoHelper;

    private QuestionBankDaoHelper(Context context) {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), "QUESTION_BANK.db", null);
        mDaoMaster = new DaoMaster(mDevOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
        mDao = mDaoSession.getQuestionBankDao();
    }

    public static QuestionBankDaoHelper getInstance(Context context) {
        if (mDaoHelper == null) {
            synchronized (QuestionBankDaoHelper.class) {
                if (mDaoHelper == null) {
                    mDaoHelper = new QuestionBankDaoHelper(context);
                }
            }
        }
        return mDaoHelper;
    }

    /**
     * add
     */
    public long insert(QuestionBank data) {
        return mDao.insert(data);
    }

    public void insertOrReplace(QuestionBank data) {
        mDao.insertOrReplace(data);
    }

    /**
     * delete all
     */
    public void deleteAll() {
        mDaoSession.deleteAll(QuestionBank.class);
    }

    /**
     * update
     */
    public void update(QuestionBank data) {
        QuestionBank old = mDao.queryBuilder().where(QuestionBankDao.Properties.Id.eq(data.getId())).build().unique();
        if (old != null) {
            mDao.update(data);
        }
    }

    public List<QuestionBank> searchById(String id) {
        QueryBuilder<QuestionBank> songQueryBuilder = mDao.queryBuilder();
        List<QuestionBank> data = songQueryBuilder.where(QuestionBankDao.Properties.Id.eq(id)).list();
        return data;
    }

    public List<QuestionBank> searchAll() {
        List<QuestionBank> data = mDao.queryBuilder().list();
        return data;
    }

}
