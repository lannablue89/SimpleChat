package com.lanna.android.simplechat.viewmodel;

import android.support.annotation.DimenRes;
import android.view.Gravity;

import com.lanna.android.simplechat.R;
import com.lanna.android.simplechat.model.ChatMessage;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class ItemChatViewModel implements ItemViewModel<ChatMessage> {

    public int gravity;
    public int background;
    public @DimenRes int dimenTop;
    public @DimenRes int dimenLeft;
    public @DimenRes int dimenRight;
    public int avatarColor;
    public int avatarVisible = 4; // invisible
    public String avatarText;
    public boolean isMe;

    private int position;
    private ChatMessage data;

    public ItemChatViewModel(int position, ChatMessage data, ChatMessage prev, ChatMessage next) {
        this.position = position;
        this.data = data;

        avatarColor = data.getColor();
        avatarText = data.getName();

        isMe = data.getUserType() == ChatMessage.UserType.ME;
        gravity = isMe ? Gravity.RIGHT : Gravity.LEFT;

        dimenLeft = isMe ? R.dimen.item_chat_margin_left_or_right : R.dimen.space_medium;
        dimenRight = isMe ? R.dimen.space_medium : R.dimen.item_chat_margin_left_or_right;

        boolean hasPrev = prev != null && prev.getUserType() == data.getUserType();
        boolean hasNext = next != null && next.getUserType() == data.getUserType();
        dimenTop = hasPrev ? R.dimen.line_normal : R.dimen.space_medium;
        avatarVisible = !isMe && !hasPrev ? 0 : 4; // View.VISIBLE : View.INVISIBLE
        background =
                hasPrev & hasNext ? R.drawable.bg_item_chat_middle
                        : hasPrev ? R.drawable.bg_item_chat_bottom
                        : hasNext ? R.drawable.bg_item_chat_top
                        : R.drawable.bg_item_chat
        ;
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
