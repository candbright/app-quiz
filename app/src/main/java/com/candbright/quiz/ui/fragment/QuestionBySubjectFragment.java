package com.candbright.quiz.ui.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.candbright.base.adapter.SortedItemList;
import com.candbright.base.fragment.BaseFragment;
import com.candbright.quiz.R;
import com.candbright.quiz.app.GlobalApp;
import com.candbright.quiz.dao.helper.QuestionDaoHelper;
import com.candbright.quiz.dao.helper.QuestionSubjectDaoHelper;
import com.candbright.quiz.databinding.FragmentQuestionBySubjectBinding;
import com.candbright.quiz.databinding.NavigationBarBinding;
import com.candbright.quiz.manager.FragmentRouteManager;
import com.candbright.quiz.manager.widget.NavigationBarManager;
import com.candbright.quiz.model.data.Question;
import com.candbright.quiz.model.data.QuestionSubject;
import com.candbright.quiz.ui.adapter.MyDiffAdapter;
import com.candbright.quiz.ui.item.QuestionInfoItem;
import com.candbright.quiz.ui.item.QuestionSubjectSelectItem;

import java.util.List;

public class QuestionBySubjectFragment extends BaseFragment<FragmentQuestionBySubjectBinding> {

    private final QuestionSubjectDaoHelper qbDaoHelper = QuestionSubjectDaoHelper.getInstance(GlobalApp.getInstance());

    private final QuestionDaoHelper qDaoHelper = QuestionDaoHelper.getInstance(GlobalApp.getInstance());

    private NavigationBarBinding navigationBarTop;

    private FragmentRouteManager<QuestionBySubjectFragment> fragmentManager;

    MyDiffAdapter<QuestionInfoItem> dataAdapter;

    SortedItemList<QuestionInfoItem> mData;


    MyDiffAdapter<QuestionSubjectSelectItem> subjectAdapter;

    SortedItemList<QuestionSubjectSelectItem> mSubjectData;
    private int currentIndex;

    private String currentSubject;

    public QuestionBySubjectFragment(String currentSubject) {
        this.currentSubject = currentSubject;
    }

    @Override
    protected void initViewBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootBinding.getRoot());
    }

    @Override
    protected void initManager() {
        NavigationBarManager navigationBarTopManager = new NavigationBarManager(navigationBarTop);
        navigationBarTopManager.setTitle(R.string.title_question_subject);
        fragmentManager = new FragmentRouteManager<>(this);
        mSubjectData = new SortedItemList<>();
        List<QuestionSubject> subjects = qbDaoHelper.searchAll();
        subjects.forEach(subject -> {
            if (subject.getSubject().equals(currentSubject)) {
                mSubjectData.add(new QuestionSubjectSelectItem(subject).setSelected(true));
            } else {
                mSubjectData.add(new QuestionSubjectSelectItem(subject));
            }
        });
        subjectAdapter = new MyDiffAdapter<>(mSubjectData.list());
        rootBinding.rvModeList.setAdapter(subjectAdapter);
        rootBinding.rvModeList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        subjectAdapter.setOnItemListener((sortedIndex, switchValue, data) -> {
            if (sortedIndex == currentIndex) {
                return;
            }
            QuestionSubjectSelectItem oldItem = subjectAdapter.getChangedItem(currentIndex);
            oldItem.setSelected(false);
            QuestionSubjectSelectItem changedItem = subjectAdapter.getChangedItem((int) sortedIndex);
            changedItem.setSelected(true);
            currentIndex = changedItem.getSortedIndex();
            subjectAdapter.notifyDiff();
            flushData(changedItem.getData().getSubject());
        });
        mData = new SortedItemList<>(true);
        if (mData.size() > 0) {
            mData.clear();
        }
        List<Question> questions = qDaoHelper.searchBySubject(currentSubject);
        questions.forEach(question -> {
            mData.add(new QuestionInfoItem(question));
        });
        dataAdapter = new MyDiffAdapter<>(mData.list());
        rootBinding.rvDataList.setAdapter(dataAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(getContext()));
        dataAdapter.setOnItemListener((sortedIndex, switchValue, data) -> {
            fragmentManager.goToSubFragment(new QuestionDetailFragment());
        });
        getRootBinding().refreshLayout.setOnRefreshListener(() -> {
            getRootBinding().refreshLayout.setRefreshing(false);
        });
    }

    public void flushData(String subject) {
        if (mData.size() > 0) {
            mData.clear();
        }
        List<Question> questions = qDaoHelper.searchBySubject(subject);
        questions.forEach(question -> {
            mData.add(new QuestionInfoItem(question));
        });
        dataAdapter.notifyDiff();
    }

}
