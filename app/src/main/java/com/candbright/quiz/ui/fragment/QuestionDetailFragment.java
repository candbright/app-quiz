package com.candbright.quiz.ui.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.candbright.base.adapter.SortedItemList;
import com.candbright.base.fragment.BaseFragment;
import com.candbright.quiz.dao.helper.QuestionDaoHelper;
import com.candbright.quiz.dao.helper.QuestionSubjectDaoHelper;
import com.candbright.quiz.databinding.FragmentQuestionBySubjectBinding;
import com.candbright.quiz.model.data.Question;
import com.candbright.quiz.model.data.QuestionSubject;
import com.candbright.quiz.ui.adapter.MyDiffAdapter;
import com.candbright.quiz.ui.item.QuestionItem;
import com.candbright.quiz.ui.item.QuestionSubjectSelectItem;

import java.util.List;

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
