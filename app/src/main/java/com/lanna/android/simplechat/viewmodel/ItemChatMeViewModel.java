package com.lanna.android.simplechat.viewmodel;

import com.lanna.android.simplechat.R;
import com.lanna.android.simplechat.model.ChatMessage;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class ItemChatMeViewModel extends ItemChatViewModel {

    public ItemChatMeViewModel(int position, ChatMessage data, ChatMessage prev, ChatMessage next) {
        super(position, data, prev, next);

        isMe = true;

        dimenLeft = R.dimen.item_chat_margin_left_or_right;
        dimenRight = R.dimen.space_medium;
    }

}
