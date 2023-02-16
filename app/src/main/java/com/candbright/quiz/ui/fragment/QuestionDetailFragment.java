package com.candbright.quiz.ui.fragment;

import com.candbright.base.fragment.BaseFragment;
import com.candbright.quiz.dao.helper.QuestionDaoHelper;
import com.candbright.quiz.dao.helper.QuestionSubjectDaoHelper;
import com.candbright.quiz.databinding.FragmentQuestionBySubjectBinding;

public class QuestionDetailFragment extends BaseFragment<FragmentQuestionBySubjectBinding> {

    private QuestionSubjectDaoHelper qbDaoHelper = QuestionSubjectDaoHelper.getInstance(this.getContext());

    private QuestionDaoHelper qDaoHelper = QuestionDaoHelper.getInstance(this.getContext());

    @Override
    protected void initViewBinding() {

    }

    @Override
    protected void initManager() {

    }

    public void flushData(String subject) {

    }

}
