package com.lanna.android.simplechat.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanna.android.simplechat.databinding.ItemChatBinding;
import com.lanna.android.simplechat.model.ChatItem;
import com.lanna.android.simplechat.viewmodel.ItemChatViewModel;
import com.lanna.android.simplechat.viewmodel.ItemViewModel;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class ChatAdapter extends BaseRecyclerAdapter<ChatItem, SimpleBindingHolder<ItemChatBinding>> {

    public ChatAdapter(OnItemClickListener<ChatItem> onItemClickListener) {
        super(onItemClickListener);
    }

    @Override
    public SimpleBindingHolder<ItemChatBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemChatBinding binding = ItemChatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SimpleBindingHolder<ItemChatBinding>(binding, onItemClickListener) {
            @Override
            ItemViewModel getViewModelOnClick(View v) {
                return binding.getViewModel();
            }
        };
    }

    @Override
    public void onBindViewHolder(SimpleBindingHolder<ItemChatBinding> holder, int position) {
        holder.binding.setViewModel(new ItemChatViewModel(getItem(position)));
    }

}
