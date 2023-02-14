package com.candbright.quiz.ui.home;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.candbright.base.adapter.SortedItemList;
import com.candbright.base.fragment.BaseFragment;
import com.candbright.base.fragment.FragmentLifecycleListener;
import com.candbright.base.fragment.IFragmentLifecycleListener;
import com.candbright.quiz.R;
import com.candbright.quiz.app.GlobalApp;
import com.candbright.quiz.dao.helper.QuestionBankDaoHelper;
import com.candbright.quiz.databinding.FragmentQuestionBankBinding;
import com.candbright.quiz.databinding.NavigationBarBinding;
import com.candbright.quiz.manager.widget.NavigationBarManager;
import com.candbright.quiz.model.adapter.MyDiffAdapter;
import com.candbright.quiz.model.data.QuestionBank;
import com.candbright.quiz.model.item.QuestionBankItem;

import java.util.List;

public class QuestionBankFragment extends BaseFragment<FragmentQuestionBankBinding> {
    private static final String TAG = "<QuestionBankFragment>";
    private NavigationBarBinding navigationBarTop;
    private NavigationBarManager navigationBarTopManager;

    MyDiffAdapter sortedAdapter;
    SortedItemList<QuestionBankItem> mData;

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
        QuestionBankDaoHelper daoHelper = QuestionBankDaoHelper.getInstance(GlobalApp.getInstance());
        List<QuestionBank> data = daoHelper.searchAll();
        for (int i = 0; i < data.size(); i++) {
            QuestionBank item = data.get(i);
            mData.add((QuestionBankItem) new QuestionBankItem().setSubject(item.getSubject())
                    .setSortedIndex(item.getId().intValue()));
        }
        sortedAdapter = new MyDiffAdapter(mData.list());
        rootBinding.rvDataList.setAdapter(sortedAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(getContext()));
        sortedAdapter.setOnItemListener((tag, switchValue, d) -> {
        });
    }

}
