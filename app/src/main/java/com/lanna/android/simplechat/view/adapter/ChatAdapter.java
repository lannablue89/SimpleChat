package com.lanna.android.simplechat.view.adapter;

import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanna.android.simplechat.databinding.ItemChatBinding;
import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.viewmodel.ItemChatViewModel;
import com.lanna.android.simplechat.viewmodel.ItemViewModel;

import java.util.List;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class ChatAdapter extends BaseRecyclerAdapter<ChatMessage, SimpleBindingHolder<ItemChatBinding>> {

    public ChatAdapter(OnItemClickListener<ChatMessage> onItemClickListener) {
        super(onItemClickListener);
    }

    @Override
    protected DiffUtil.Callback newDiffCallback(List<ChatMessage> oldList, List<ChatMessage> newList) {
        return new ChatDiffCallback(oldList, newList);
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


    /*
        DiffCallback
     */
    static class ChatDiffCallback extends DiffUtil.Callback {

        private List<ChatMessage> oldList;
        private List<ChatMessage> newList;

        public ChatDiffCallback(List<ChatMessage> oldList, List<ChatMessage> newList) {
            this.newList = newList;
            this.oldList = oldList;
        }

        @Override
        public int getOldListSize() {
            return oldList != null ? oldList.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return newList != null ? newList.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return true;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
        }
    }
    // end of DiffCallback

}
