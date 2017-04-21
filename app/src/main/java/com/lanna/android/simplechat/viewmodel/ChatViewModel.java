package com.lanna.android.simplechat.viewmodel;

import android.databinding.Bindable;
import android.graphics.Color;
import android.text.TextUtils;

import com.lanna.android.simplechat.BR;
import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.model.ChatServiceModel;
import com.lanna.android.simplechat.util.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lanna on 4/19/17.
 */

public class ChatViewModel extends ListViewModel<ChatMessage> {

    private String input;
    private int selection;

    private int chatId = 0;


    public void startChatFlow() {
        new ChatServiceModel(chatId).startNormalMessageFlow(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) { }

            @Override
            public void onNext(String value) {
//                LogUtils.w("onNext: " + value);
                if (!TextUtils.isEmpty(value)) {
                    newMessage(new ChatMessage(getNextChatId(), ChatMessage.UserType.OTHER, "other", value, Color.GREEN));
                }
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.w("onError: " + t.getMessage());
            }

            @Override
            public void onComplete() { }
        });
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

    public void newMessage(ChatMessage item) {
        items.add(item);
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

    }
}
