package com.candbright.quiz.ui.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.DataItem;
import com.candbright.base.adapter.SortedItem;
import com.candbright.quiz.databinding.ItemBarSelectorBinding;
import com.candbright.quiz.model.data.QuestionSubject;
import com.candbright.quiz.ui.holder.QuestionSubjectSelectHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionSubjectSelectItem extends DataItem<QuestionSubject, QuestionSubjectSelectHolder, ItemBarSelectorBinding> {
    //是否选中
    private boolean isSelected = false;

    public QuestionSubjectSelectItem(QuestionSubject questionSubject) {
        super(questionSubject);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public QuestionSubjectSelectItem setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }

    @Override
    protected QuestionSubjectSelectHolder createViewHolder(ItemBarSelectorBinding viewBinding) {
        return new QuestionSubjectSelectHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public QuestionSubjectSelectItem clone() {
        return (QuestionSubjectSelectItem) super.clone();
    }
}
