package com.candbright.quiz.dao.helper;

import com.candbright.quiz.dao.QuestionDao;
import com.candbright.quiz.model.data.Question;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>created by wyh in 2021/12/13</p>
 */
public class QuestionDaoHelper extends BaseDaoHelper implements IDaoHelper<Question> {

    private static QuestionDaoHelper mDaoHelper;
    private QuestionDao mDao;

    @Override
    protected String tableName() {
        return QuestionDao.TABLENAME;
    }

    private QuestionDaoHelper() {
        super();
        mDao = mDaoSession.getQuestionDao();
    }

    public static QuestionDaoHelper getInstance() {
        if (mDaoHelper == null) {
            synchronized (QuestionDaoHelper.class) {
                if (mDaoHelper == null) {
                    mDaoHelper = new QuestionDaoHelper();
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

    public List<Question> searchAll() {
        List<Question> data = mDao.queryBuilder().list();
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public List<Question> searchById(String id) {
        QueryBuilder<Question> songQueryBuilder = mDao.queryBuilder();
        List<Question> data = songQueryBuilder.where(QuestionDao.Properties.Id.eq(id)).list();
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public List<Question> searchBySubject(String subject) {
        QueryBuilder<Question> songQueryBuilder = mDao.queryBuilder();
        List<Question> data;
        if (subject.equals("subject_all")) {
            data = searchAll();
        } else {
            data = songQueryBuilder.where(QuestionDao.Properties.Subject.eq(subject)).list();
        }
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }
}
