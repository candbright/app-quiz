package com.candbright.quiz.dao.helper;

import com.candbright.quiz.app.GlobalApp;
import com.candbright.quiz.dao.DaoMaster;
import com.candbright.quiz.dao.DaoSession;
import com.candbright.quiz.dao.QuestionDao;

import org.greenrobot.greendao.AbstractDao;

public abstract class BaseDaoHelper {

    protected DaoMaster.DevOpenHelper mDevOpenHelper;

    protected DaoMaster mDaoMaster;

    protected DaoSession mDaoSession;


    public BaseDaoHelper() {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(GlobalApp.getInstance().getApplicationContext(), tableName(), null);
        mDaoMaster = new DaoMaster(mDevOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
    }

    protected abstract String tableName();

}
