package com.candbright.quiz.ui.home;

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
import com.candbright.quiz.model.item.QuestionSubjectSelectItem;

import java.util.List;

public class QuestionBySubjectFragment extends BaseFragment<FragmentQuestionSubjectBinding> {

    private QuestionSubjectDaoHelper qbDaoHelper = QuestionSubjectDaoHelper.getInstance(this.getContext());

    private QuestionDaoHelper qDaoHelper = QuestionDaoHelper.getInstance(this.getContext());

    MyDiffAdapter dataAdapter;
    SortedItemList<QuestionItem> mData;


    MyDiffAdapter subjectAdapter;

    SortedItemList<QuestionSubjectSelectItem> mSubjectData;
    int oldModeIndex;

    @Override
    protected void initViewBinding() {

    }

    @Override
    protected void initManager() {
        mSubjectData = new SortedItemList();
        List<QuestionSubject> subjects = qbDaoHelper.searchAll();
        subjects.stream().forEach(subject -> {
            mSubjectData.add(new QuestionSubjectSelectItem().setStrRes(subject.getSubject()));
        });
        subjectAdapter = new MyDiffAdapter(mSubjectData.list());
        rootBinding.rvModeList.setAdapter(subjectAdapter);
        rootBinding.rvModeList.setLayoutManager(new LinearLayoutManager(getContext()));
        subjectAdapter.setOnItemListener((sortedIndex, switchValue, data) -> {
            if (sortedIndex == oldModeIndex) {
                return;
            }
            QuestionSubjectSelectItem oldItem = (QuestionSubjectSelectItem) subjectAdapter.getChangedItem(oldModeIndex);
            oldItem.setSelected(false);
            QuestionSubjectSelectItem changedItem = (QuestionSubjectSelectItem) subjectAdapter.getChangedItem((int) sortedIndex);
            changedItem.setSelected(true);
            oldModeIndex = changedItem.getSortedIndex();
            subjectAdapter.notifyDiff();
            flushData(changedItem.getStrRes());
        });

        mData = new SortedItemList(true);
        List<Question> questions = qDaoHelper.searchAll();
        questions.stream().forEach(question -> {
            mData.add(new QuestionItem(question));
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
            mData.add(new QuestionItem(question));
        });
        dataAdapter.notifyDiff();
    }

}
