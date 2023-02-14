package com.candbright.quiz.model.holder;

import android.view.View;

import com.candbright.base.adapter.BaseViewHolder;
import com.candbright.quiz.databinding.ItemQuestionBankBinding;
import com.candbright.quiz.model.item.QuestionBankItem;
import com.candbright.quiz.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionBankHolder extends BaseViewHolder<QuestionBankItem, ItemQuestionBankBinding> {
    private static final String TAG = QuestionBankHolder.class.getSimpleName();

    public QuestionBankHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(QuestionBankItem data) {
        rootBinding.tvSubject.setText(Utility.getString("subject_" + data.getSubject()));
        rootBinding.cardRoot.setOnClickListener(v -> {
            mListener.onItemEvent(data.getSortedIndex(), null);
        });
    }
}
