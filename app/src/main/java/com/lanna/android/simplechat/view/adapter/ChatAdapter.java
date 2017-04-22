package com.lanna.android.simplechat.view.adapter;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanna.android.simplechat.databinding.ItemChatMeBinding;
import com.lanna.android.simplechat.databinding.ItemChatOtherBinding;
import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.viewmodel.ItemChatViewModel;
import com.lanna.android.simplechat.viewmodel.ItemViewModel;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class ChatAdapter extends BaseRecyclerAdapter<ChatMessage, SimpleBindingHolder> {

    public ChatAdapter(OnItemClickListener<ChatMessage> onItemClickListener) {
        super(onItemClickListener);
    }

//    @Override
//    protected DiffUtil.Callback newDiffCallback(List<ChatMessage> oldList, List<ChatMessage> newList) {
//        return new ChatDiffCallback(oldList, newList);
//    }

    @Override
    public SimpleBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        if (viewType == ChatMessage.UserType.ME) {
            binding = ItemChatMeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        } else {
            binding = ItemChatOtherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        }

        return new SimpleBindingHolder<ViewDataBinding>(binding, onItemClickListener) {
            @Override
            ItemViewModel getViewModelOnClick(View v) {
                return binding instanceof ItemChatMeBinding
                        ? ((ItemChatMeBinding) binding).getViewModel()
                        : ((ItemChatOtherBinding) binding).getViewModel();
            }
        };
    }

    @Override
    public void onBindViewHolder(SimpleBindingHolder holder, int position) {
        ItemChatViewModel viewModel = new ItemChatViewModel();
        viewModel.bindData(position, getItem(position), getItem(position - 1), getItem(position + 1));

        if (holder.binding instanceof ItemChatMeBinding) {
            ((ItemChatMeBinding) holder.binding).setViewModel(viewModel);

        } else {
            ((ItemChatOtherBinding) holder.binding).setViewModel(viewModel);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getUserType();
    }

//    @Override
//    public void setItemsAndNotifyEach(List<ChatMessage> chatMessages) {
//        super.setItemsAndNotifyEach(chatMessages);
//        notifyItemChanged(getItemCount() - 2);
//    }

    /*
        DiffCallback
     */
//    static class ChatDiffCallback extends DiffUtil.Callback {
//
//        private List<ChatMessage> oldList;
//        private List<ChatMessage> newList;
//
//        public ChatDiffCallback(List<ChatMessage> oldList, List<ChatMessage> newList) {
//            this.newList = newList;
//            this.oldList = oldList;
//        }
//
//        @Override
//        public int getOldListSize() {
//            return oldList != null ? oldList.size() : 0;
//        }
//
//        @Override
//        public int getNewListSize() {
//            return newList != null ? newList.size() : 0;
//        }
//
//        @Override
//        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//            return true;
//        }
//
//        @Override
//        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
//        }
//    }
    // end of DiffCallback

}
