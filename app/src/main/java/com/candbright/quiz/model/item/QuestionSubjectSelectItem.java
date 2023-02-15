package com.candbright.quiz.model.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.SortedItem;
import com.candbright.quiz.databinding.ItemBarSelectorBinding;
import com.candbright.quiz.model.holder.QuestionSubjectSelectHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionSubjectSelectItem extends SortedItem<QuestionSubjectSelectHolder, ItemBarSelectorBinding> {
    //item内容
    private String strRes;

    private int intRes;
    //是否选中
    private boolean isSelected = false;

    public String getStrRes() {
        return strRes;
    }

    public QuestionSubjectSelectItem setStrRes(String strRes) {
        this.strRes = strRes;
        return this;
    }

    public int getIntRes() {
        return intRes;
    }

    public QuestionSubjectSelectItem setIntRes(int intRes) {
        this.intRes = intRes;
        return this;
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
