package com.lanna.android.simplechat;

import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.util.Constant;
import com.lanna.android.simplechat.viewmodel.ItemChatViewModel;

import org.junit.Test;

import static com.lanna.android.simplechat.model.ChatMessage.UserType.ME;
import static com.lanna.android.simplechat.model.ChatMessage.UserType.OTHER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class ItemChatViewModelTest extends ApplicationTestCase {

    private final int BG_SINGLE = R.drawable.bg_item_chat;
    private final int BG_TOP = R.drawable.bg_item_chat_top;
    private final int BG_MIDDLE = R.drawable.bg_item_chat_middle;
    private final int BG_BOTTOM = R.drawable.bg_item_chat_bottom;

    private ChatMessage message;
    private ChatMessage messagePrev;
    private ChatMessage messageNext;
    private ItemChatViewModel model;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        message = spy(new ChatMessage(0, ME, "test", "message", 0));
        messagePrev = spy(new ChatMessage());
        messageNext = spy(new ChatMessage());
        model = spy(new ItemChatViewModel());
    }

    @Test
    public void testNotDebugEnv() {
        assertThat(Constant.Test.isDebug, is(false));
    }

    @Test
    public void tesItemChatSimpleStuffs() throws Exception {
        model.bindData(0, message, null, null);

        assertThat(model.isMe, is(true));
        assertThat(model.avatarColor, is(message.getColor()));
        assertThat(model.avatarName, is("T"));
        assertThat(model.message, is(message.getMessage()));
    }


    @Test
    public void testMe() {
        message.setUserType(ME);
        model.bindData(0, message, null, null);

        assertThat(model.dimenLeft, is(R.dimen.item_chat_margin_horizontal_large));
        assertThat(model.dimenRight, is(R.dimen.item_chat_margin_horizontal_normal));
    }

    @Test
    public void testNotMe() {
        message.setUserType(OTHER);
        model.bindData(0, message, null, null);

        assertThat(model.dimenLeft, is(R.dimen.item_chat_margin_horizontal_normal));
        assertThat(model.dimenRight, is(R.dimen.item_chat_margin_horizontal_large));
    }


    @Test
    public void testItemChat_LayoutSingle() throws Exception { // no same items from prev or next
        model.bindData(0, message, null, null);
        verifyItemChat_LayoutSingle();

        messagePrev.setUserType(OTHER);
        messageNext.setUserType(OTHER);
        model.bindData(0, message, messagePrev, messageNext);
        verifyItemChat_LayoutSingle();
    }

    private void verifyItemChat_LayoutSingle() {
        assertThat(model.dimenTop, is(R.dimen.item_chat_divider_large));
        assertThat(model.avatarVisible, is(Constant.View.VISIBLE));
        assertThat(model.background, is(BG_SINGLE));
    }


    @Test
    public void testItemChatLayout_Single() throws Exception {
        // SINGLE
        messagePrev.setUserType(OTHER);
        message.setUserType(ME);
        messageNext.setUserType(OTHER);
        model.bindData(0, message, messagePrev, messageNext);

        assertThat(model.dimenTop, is(R.dimen.item_chat_divider_large));
        assertThat(model.avatarVisible, is(Constant.View.VISIBLE));
        assertThat(model.background, is(BG_SINGLE));
    }

    @Test
    public void testItemChatLayout_Bottom() throws Exception {
        // BOTTOM
        messagePrev.setUserType(ME);
        message.setUserType(ME);
        messageNext.setUserType(OTHER);
        model.bindData(0, message, messagePrev, messageNext);

        assertThat(model.dimenTop, is(R.dimen.item_chat_divider_normal));
        assertThat(model.avatarVisible, is(Constant.View.VISIBLE));
        assertThat(model.background, is(BG_BOTTOM));
    }

    @Test
    public void testItemChatLayout_Middle() throws Exception {
        // MIDDLE
        messagePrev.setUserType(ME);
        message.setUserType(ME);
        messageNext.setUserType(ME);
        model.bindData(0, message, messagePrev, messageNext);

        assertThat(model.dimenTop, is(R.dimen.item_chat_divider_normal));
        assertThat(model.avatarVisible, is(Constant.View.INVISIBLE));
        assertThat(model.background, is(BG_MIDDLE));
    }

    @Test
    public void testItemChatLayout_Top() throws Exception {
        // TOP
        messagePrev.setUserType(OTHER);
        message.setUserType(ME);
        messageNext.setUserType(ME);
        model.bindData(0, message, messagePrev, messageNext);

        assertThat(model.dimenTop, is(R.dimen.item_chat_divider_large));
        assertThat(model.avatarVisible, is(Constant.View.INVISIBLE));
        assertThat(model.background, is(BG_TOP));
    }

}