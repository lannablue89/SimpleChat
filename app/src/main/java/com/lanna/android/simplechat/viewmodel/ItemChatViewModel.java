package com.lanna.android.simplechat.viewmodel;

import com.lanna.android.simplechat.model.ChatItem;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class ItemChatViewModel implements ItemViewModel<ChatItem> {

    private ChatItem data;

    public ItemChatViewModel(ChatItem data) {
        this.data = data;
    }

    public String getContent() {
        return data.getText();
    }

    @Override
    public ChatItem getData() {
        return data;
    }
}
