package com.lanna.android.simplechat.viewmodel;

import android.databinding.ObservableField;

import com.lanna.android.simplechat.model.ChatItem;
import com.lanna.android.simplechat.view.adapter.ChatAdapter;

/**
 * Created by lanna on 4/19/17.
 */

public class ChatViewModel extends RecyclerViewModel<ChatItem,ChatAdapter> {

    public ObservableField<String> input = new ObservableField<>();

    public ChatViewModel(ChatAdapter adapter) {
        super(adapter);
    }

    public void sent() {
        items.add(new ChatItem(input.get()));
        input.set("");
    }

    @Override
    public void onDestroy() {

    }
}
