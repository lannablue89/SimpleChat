package com.lanna.android.simplechat.view.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanna.android.simplechat.viewmodel.ItemViewModel;


/**
 * Created by lanna on 8/11/16.
 *
 */

abstract class SimpleBindingHolder<VDB extends ViewDataBinding> extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    VDB binding;
    private BaseRecyclerAdapter.OnItemClickListener onItemClickListener;

    SimpleBindingHolder(VDB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    SimpleBindingHolder(VDB binding, BaseRecyclerAdapter.OnItemClickListener onItemClickListener) {
        this(binding);
        this.onItemClickListener = onItemClickListener;
        binding.getRoot().setOnClickListener(this);
    }

    abstract ItemViewModel getViewModelOnClick(View v);

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            ItemViewModel viewModel = getViewModelOnClick(v);
            onItemClickListener.onItemClicked(v, getAdapterPosition(), viewModel == null ? null : viewModel.getData());
        }
    }
}
