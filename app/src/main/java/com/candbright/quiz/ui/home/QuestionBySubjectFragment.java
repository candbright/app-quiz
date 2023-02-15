package com.candbright.quiz.ui.home;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.candbright.base.adapter.SortedItemList;
import com.candbright.base.fragment.BaseFragment;
import com.candbright.quiz.dao.helper.QuestionDaoHelper;
import com.candbright.quiz.dao.helper.QuestionSubjectDaoHelper;
import com.candbright.quiz.databinding.FragmentQuestionSubjectBinding;
import com.candbright.quiz.model.adapter.MyDiffAdapter;
import com.candbright.quiz.model.data.Question;
import com.candbright.quiz.model.data.QuestionSubject;
import com.candbright.quiz.model.item.QuestionItem;
import com.candbright.quiz.model.item.SelectorBarItem;
import com.candbright.quiz.util.Utility;

import java.util.List;

public class QuestionBySubjectFragment extends BaseFragment<FragmentQuestionSubjectBinding> {

    private QuestionSubjectDaoHelper qbDaoHelper = QuestionSubjectDaoHelper.getInstance(this.getContext());

    private QuestionDaoHelper qDaoHelper = QuestionDaoHelper.getInstance(this.getContext());

    MyDiffAdapter dataAdapter;
    SortedItemList<QuestionItem> mData;


    MyDiffAdapter subjectAdapter;

    SortedItemList<SelectorBarItem> mSubjectData;
    int oldModeIndex;

    @Override
    protected void initViewBinding() {

    }

    @Override
    protected void initManager() {
        mSubjectData = new SortedItemList();
        List<QuestionSubject> subjects = qbDaoHelper.searchAll();
        subjects.stream().forEach(subject -> {
            mSubjectData.add(new SelectorBarItem().setStrRes(subject.getSubject()));
        });
        subjectAdapter = new MyDiffAdapter(mSubjectData.list());
        rootBinding.rvModeList.setAdapter(subjectAdapter);
        rootBinding.rvModeList.setLayoutManager(new LinearLayoutManager(getContext()));
        subjectAdapter.setOnItemListener((sortedIndex, switchValue, data) -> {
            if (sortedIndex == oldModeIndex) {
                return;
            }
            SelectorBarItem oldItem = (SelectorBarItem) subjectAdapter.getChangedItem(oldModeIndex);
            oldItem.setSelected(false);
            SelectorBarItem changedItem = (SelectorBarItem) subjectAdapter.getChangedItem((int) sortedIndex);
            changedItem.setSelected(true);
            oldModeIndex = changedItem.getSortedIndex();
            subjectAdapter.notifyDiff();
            flushData(changedItem.getStrRes());
        });

        mData = new SortedItemList(true);
        List<QuestionSubject> questions = qbDaoHelper.searchAll();
        questions.stream().forEach(question -> {
            //todo
            mData.add(new QuestionItem().setSubject(question.getSubject()));
        });
        dataAdapter = new MyDiffAdapter(mData.list());
        rootBinding.rvDataList.setAdapter(dataAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(getContext()));
        dataAdapter.setOnItemListener((sortedIndex, switchValue, data) -> {
        });
    }

    public void flushData(String subject) {
        mData.clear();
        List<Question> questions = qDaoHelper.searchBySubject(subject);
        questions.stream().forEach(question -> {
            //todo
            mData.add(new QuestionItem().setSubject(question.getSubject()));
        });
        dataAdapter.notifyDiff();
    }

}
