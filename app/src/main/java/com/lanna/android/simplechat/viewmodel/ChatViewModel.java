package com.lanna.android.simplechat.viewmodel;

import android.databinding.Bindable;
import android.graphics.Color;
import android.text.TextUtils;

import com.lanna.android.simplechat.BR;
import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.util.LogUtils;
import com.lanna.android.simplechat.view.service.MyRxBus;

import java.util.List;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by lanna on 4/19/17.
 */

public class ChatViewModel extends ListViewModel<ChatMessage> {

    private String input;
    private int selection;
    private int chatId;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void refreshData() {
        compositeSubscription.add(MyRxBus.getInstance().getEvents().subscribe(new Subscriber<List<ChatMessage>>() {

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) {
                LogUtils.w(ChatViewModel.class, "onNext: " + e.getMessage());
            }

            @Override
            public void onNext(List<ChatMessage> chatMessages) {
                LogUtils.i(ChatViewModel.class, "onNext: " + chatMessages.get(chatMessages.size()-1));
                setMessages(chatMessages);
            }
        }));
    }

    @Bindable
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
        notifyPropertyChanged(BR.input);
    }

    @Bindable
    public int getSelection() {
        return selection;
    }

    public void setMessages(List<ChatMessage> items) {
        this.items = items;
        notifyDataChanged();
    }

    public void newMessage(ChatMessage item) {
        items.add(item);
        notifyDataChanged();
    }

    private void notifyDataChanged() {
        selection = items.size() - 1;
        notifyPropertyChanged(BR.items);
        notifyPropertyChanged(BR.selection);
    }

    public void sent() {
        if (!TextUtils.isEmpty(input)) {
            newMessage(new ChatMessage(getNextChatId(), ChatMessage.UserType.ME, "me", input.trim(), Color.BLUE));
            input = "";
            notifyPropertyChanged(BR.input);
        }
    }

    private int getNextChatId() {
        return ++chatId;
    }

    @Override
    public void onDestroy() {
        compositeSubscription.clear(); // unsubscribe and clear
    }
}
