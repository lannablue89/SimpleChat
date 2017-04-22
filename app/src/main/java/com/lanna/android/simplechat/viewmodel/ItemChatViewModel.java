package com.lanna.android.simplechat.viewmodel;

import android.databinding.BaseObservable;
import android.support.annotation.DimenRes;

import com.lanna.android.simplechat.R;
import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.util.Constant;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class ItemChatViewModel extends BaseObservable implements ItemViewModel<ChatMessage> {

    public int avatarColor;
    public int avatarVisible = Constant.View.INVISIBLE;
    public String avatarName;

    public int background;
    public @DimenRes int dimenTop;
    public @DimenRes int dimenLeft;
    public @DimenRes int dimenRight;

    public String message;
    public boolean isMe;

    private int position;
    protected ChatMessage data;


    public void bindData(int position, ChatMessage data, ChatMessage prev, ChatMessage next) {
        this.position = position;
        this.data = data;
        isMe = data.getUserType() == ChatMessage.UserType.ME;

        avatarColor = data.getColor();
        avatarName = data.getName().substring(0,1).toUpperCase();

        message = data.getMessage();
        if (Constant.Test.isDebug) {
            message =  position + " - " + message;
        }

        dimenLeft = isMe ? R.dimen.item_chat_margin_horizontal_large : R.dimen.item_chat_margin_horizontal_normal;
        dimenRight = isMe ? R.dimen.item_chat_margin_horizontal_normal : R.dimen.item_chat_margin_horizontal_large;


        boolean hasPrev = prev != null && prev.getUserType() == data.getUserType();
        boolean hasNext = next != null && next.getUserType() == data.getUserType();
        dimenTop = hasPrev ? R.dimen.item_chat_divider_normal : R.dimen.item_chat_divider_large;
        avatarVisible = !hasNext ? Constant.View.VISIBLE : Constant.View.INVISIBLE;
        background =
                hasPrev & hasNext ? R.drawable.bg_item_chat_middle
                        : hasPrev ? R.drawable.bg_item_chat_bottom
                        : hasNext ? R.drawable.bg_item_chat_top
                        : R.drawable.bg_item_chat
        ;
    }

    @Override
    public ChatMessage getData() {
        return data;
    }
}
