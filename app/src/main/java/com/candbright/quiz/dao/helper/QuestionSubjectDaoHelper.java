package com.candbright.quiz.dao.helper;

import com.candbright.quiz.dao.DaoMaster;
import com.candbright.quiz.dao.DaoSession;
import com.candbright.quiz.dao.QuestionSubjectDao;
import com.candbright.quiz.model.data.QuestionSubject;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>created by wyh in 2021/12/13</p>
 */
public class QuestionSubjectDaoHelper extends BaseDaoHelper implements IDaoHelper<QuestionSubject> {
    private QuestionSubjectDao mDao;
    private static QuestionSubjectDaoHelper mDaoHelper;

    @Override
    protected String tableName() {
        return QuestionSubjectDao.TABLENAME;
    }

    private QuestionSubjectDaoHelper() {
        super();
        mDao = mDaoSession.getQuestionSubjectDao();
    }

    public static QuestionSubjectDaoHelper getInstance() {
        if (mDaoHelper == null) {
            synchronized (QuestionSubjectDaoHelper.class) {
                if (mDaoHelper == null) {
                    mDaoHelper = new QuestionSubjectDaoHelper();
                }
            }
        }
        return mDaoHelper;
    }

    /**
     * add
     */
    public long insert(QuestionSubject data) {
        return mDao.insert(data);
    }

    /**
     * add
     */
    public void insert(List<QuestionSubject> data) {
        for (QuestionSubject datum : data) {
            mDao.insert(datum);
        }
    }

    public void insertOrReplace(QuestionSubject data) {
        mDao.insertOrReplace(data);
    }

    /**
     * delete all
     */
    public void deleteAll() {
        mDaoSession.deleteAll(QuestionSubject.class);
    }

    /**
     * update
     */
    public void update(QuestionSubject data) {
        QuestionSubject old = mDao.queryBuilder().where(QuestionSubjectDao.Properties.Id.eq(data.getId())).build().unique();
        if (old != null) {
            mDao.update(data);
        }
    }

    public List<QuestionSubject> searchAll() {
        List<QuestionSubject> data = new ArrayList<>();
        data.add(new QuestionSubject(0L, "subject_all"));
        data.addAll(mDao.queryBuilder().list());
        return data;
    }

    public List<QuestionSubject> searchById(String id) {
        QueryBuilder<QuestionSubject> songQueryBuilder = mDao.queryBuilder();
        List<QuestionSubject> data = songQueryBuilder.where(QuestionSubjectDao.Properties.Id.eq(id)).list();
        return data;
    }
}
