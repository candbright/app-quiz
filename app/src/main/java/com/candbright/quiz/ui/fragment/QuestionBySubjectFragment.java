package com.candbright.quiz.ui.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.candbright.base.adapter.SortedItemList;
import com.candbright.base.fragment.BaseFragment;
import com.candbright.quiz.R;
import com.candbright.quiz.dao.helper.QuestionDaoHelper;
import com.candbright.quiz.dao.helper.QuestionSubjectDaoHelper;
import com.candbright.quiz.databinding.FragmentQuestionBySubjectBinding;
import com.candbright.quiz.databinding.NavigationBarBinding;
import com.candbright.quiz.manager.FragmentRouteManager;
import com.candbright.quiz.manager.widget.NavigationBarManager;
import com.candbright.quiz.model.data.Question;
import com.candbright.quiz.model.data.QuestionSubject;
import com.candbright.quiz.ui.adapter.MyDiffAdapter;
import com.candbright.quiz.ui.item.QuestionItem;
import com.candbright.quiz.ui.item.QuestionSubjectItem;
import com.candbright.quiz.ui.item.QuestionSubjectSelectItem;

import java.util.List;

public class QuestionBySubjectFragment extends BaseFragment<FragmentQuestionBySubjectBinding> {

    private final QuestionSubjectDaoHelper qbDaoHelper = QuestionSubjectDaoHelper.getInstance(this.getContext());

    private final QuestionDaoHelper qDaoHelper = QuestionDaoHelper.getInstance(this.getContext());

    private NavigationBarBinding navigationBarTop;

    private NavigationBarManager navigationBarTopManager;

    private FragmentRouteManager<QuestionBySubjectFragment> fragmentManager;

    MyDiffAdapter<QuestionItem> dataAdapter;

    SortedItemList<QuestionItem> mData;


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
        navigationBarTopManager = new NavigationBarManager(navigationBarTop);
        navigationBarTopManager.setTitle(R.string.title_question_subject);
        fragmentManager = new FragmentRouteManager<>(this);
        mSubjectData = new SortedItemList<>();
        List<QuestionSubject> subjects = qbDaoHelper.searchAll();
        subjects.forEach(subject -> {
            mSubjectData.add(new QuestionSubjectSelectItem(subject));
        });
        subjectAdapter = new MyDiffAdapter<>(mSubjectData.list());
        rootBinding.rvModeList.setAdapter(subjectAdapter);
        rootBinding.rvModeList.setLayoutManager(new LinearLayoutManager(getContext()));
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
        flushData(currentSubject);
        dataAdapter = new MyDiffAdapter<>(mData.list());
        rootBinding.rvDataList.setAdapter(dataAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(getContext()));
        dataAdapter.setOnItemListener((sortedIndex, switchValue, data) -> {
            QuestionItem changedItem = dataAdapter.getChangedItem((int) sortedIndex);
            fragmentManager.goToSubFragment(new QuestionDetailFragment());
        });
    }

    public void flushData(String subject) {
        if (mData.size() > 0) {
            mData.clear();
        }
        List<Question> questions = qDaoHelper.searchBySubject(subject);
        questions.forEach(question -> {
            mData.add(new QuestionItem(question));
        });
        dataAdapter.notifyDiff();
    }

}
