package com.candbright.quiz.model.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.SortedItem;
import com.candbright.quiz.databinding.ItemBarSelectorBinding;
import com.candbright.quiz.model.holder.SelectorBarHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class SelectorBarItem extends SortedItem<SelectorBarHolder, ItemBarSelectorBinding> {
    //item内容
    private String strRes;

    private int intRes;
    //是否选中
    private boolean isSelected = false;

    public String getStrRes() {
        return strRes;
    }

    public SelectorBarItem setStrRes(String strRes) {
        this.strRes = strRes;
        return this;
    }

    public int getIntRes() {
        return intRes;
    }

    public SelectorBarItem setIntRes(int intRes) {
        this.intRes = intRes;
        return this;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public SelectorBarItem setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }

    @Override
    protected SelectorBarHolder createViewHolder(ItemBarSelectorBinding viewBinding) {
        return new SelectorBarHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public SelectorBarItem clone() {
        return (SelectorBarItem) super.clone();
    }
}
