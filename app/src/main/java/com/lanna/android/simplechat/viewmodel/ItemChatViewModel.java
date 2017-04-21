package com.lanna.android.simplechat.viewmodel;

import android.databinding.ObservableInt;
import android.view.Gravity;

import com.lanna.android.simplechat.model.ChatMessage;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class ItemChatViewModel implements ItemViewModel<ChatMessage> {

    public ObservableInt gravity = new ObservableInt(Gravity.LEFT);

    private int position;
    private ChatMessage data;

    public ItemChatViewModel(int position, ChatMessage data) {
        this.position = position;
        this.data = data;
        gravity.set(Gravity.CENTER_VERTICAL | (data.getUserType() == ChatMessage.UserType.ME ? Gravity.RIGHT : Gravity.LEFT));
    }

    public String getContent() {
        return //position + " - " +
                data.getMessage();
    }

    @Override
    public ChatMessage getData() {
        return data;
    }
}
