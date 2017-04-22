package com.lanna.android.simplechat;

import android.graphics.Color;

import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.viewmodel.ChatViewModel;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


public class ChatViewModelTest extends ApplicationTestCase {

    private ChatViewModel model;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        model = spy(new ChatViewModel());
    }

    @Test
    public void tesAddChatMe() throws Exception {
        String testMessage = "lanna test message";
        model.setInput(testMessage);

        model.sent();
        verify(model).newMessage(any());

        assertThat(1, is(model.items.size()));
        assertThat(testMessage, is(model.items.get(0).getMessage()));
        assertThat(ChatMessage.UserType.ME, is(model.items.get(0).getUserType()));
    }


    @Test
    public void tesAddChatOther() throws Exception {
        String testMessage = "other test message";
        model.newMessage(new ChatMessage(-1, ChatMessage.UserType.OTHER, "A", testMessage, Color.BLUE));

        assertThat(1, is(model.items.size()));
        assertThat(testMessage, is(model.items.get(0).getMessage()));
        assertThat(ChatMessage.UserType.OTHER, is(model.items.get(0).getUserType()));
    }

}