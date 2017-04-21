package com.lanna.android.simplechat;

import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.viewmodel.ChatViewModel;

import junit.framework.Assert;

import org.junit.Test;


public class ChatViewModelUnitTest extends Assert {
    @Test
    public void addChatMe_isCorrect() throws Exception {
        ChatViewModel model = new ChatViewModel();
        String testMessage = "lanna test message";
        model.input.set(testMessage);
        assertEquals(model.items.size(), 1);
        assertEquals(model.items.get(0).getMessage(), testMessage);
        assertEquals(model.items.get(0).getUserType(), ChatMessage.UserType.ME);
    }
}