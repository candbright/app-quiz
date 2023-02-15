package com.candbright.quiz.ui.home;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.candbright.base.adapter.SortedItemList;
import com.candbright.base.fragment.BaseFragment;
import com.candbright.base.fragment.FragmentLifecycleListener;
import com.candbright.base.fragment.IFragmentLifecycleListener;
import com.candbright.quiz.R;
import com.candbright.quiz.app.GlobalApp;
import com.candbright.quiz.dao.helper.QuestionSubjectDaoHelper;
import com.candbright.quiz.databinding.FragmentQuestionBankBinding;
import com.candbright.quiz.databinding.NavigationBarBinding;
import com.candbright.quiz.manager.widget.NavigationBarManager;
import com.candbright.quiz.model.adapter.MyDiffAdapter;
import com.candbright.quiz.model.data.QuestionSubject;
import com.candbright.quiz.model.item.QuestionSubjectItem;

import java.util.List;

public class SelectQuestionSubjectFragment extends BaseFragment<FragmentQuestionBankBinding> {
    private static final String TAG = "<QuestionBankFragment>";
    private NavigationBarBinding navigationBarTop;
    private NavigationBarManager navigationBarTopManager;

    MyDiffAdapter dataAdapter;
    SortedItemList<QuestionSubjectItem> mData;

    @Override
    protected void initViewBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootBinding.getRoot());
    }

    @Override
    protected IFragmentLifecycleListener createLifecycleListener() {
        return new FragmentLifecycleListener() {
            @Override
            public void onViewCreated() {
                super.onViewCreated();
                initData();
            }
        };
    }

    @Override
    protected void initManager() {
        navigationBarTopManager = new NavigationBarManager(navigationBarTop);
        navigationBarTopManager.setTitle(R.string.title_question_bank);
        getRootBinding().refreshLayout.setOnRefreshListener(() -> {
            getRootBinding().refreshLayout.setRefreshing(false);
        });
    }

    private void initData() {
        //主界面数据
        mData = new SortedItemList(true);
        QuestionSubjectDaoHelper daoHelper = QuestionSubjectDaoHelper.getInstance(GlobalApp.getInstance());
        List<QuestionSubject> data = daoHelper.searchAll();
        data.stream().forEach(datum -> {
            mData.add((QuestionSubjectItem) new QuestionSubjectItem(datum)
                    .setSortedIndex(datum.getId().intValue()));
        });
        dataAdapter = new MyDiffAdapter(mData.list());
        rootBinding.rvDataList.setAdapter(dataAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(getContext()));
        dataAdapter.setOnItemListener((tag, switchValue, d) -> {
        });
    }

}
