package com.lanna.android.simplechat.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.model.ChatServiceModel;
import com.lanna.android.simplechat.util.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lanna on 4/19/17.
 */

public class ChatViewModel extends ListViewModel<ChatMessage> {

    public ObservableField<String> input = new ObservableField<>();
    public ObservableInt selection = new ObservableInt(0);

    private int chatId = 0;


    public void startChatFlow() {
        new ChatServiceModel(chatId).startNormalMessageFlow(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) { }

            @Override
            public void onNext(String value) {
//                LogUtils.w("onNext: " + value);
                newMessage(new ChatMessage(getNextChatId(), ChatMessage.UserType.OTHER, value));
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.w("onError: " + t.getMessage());
            }

            @Override
            public void onComplete() { }
        });
    }

    private void newMessage(ChatMessage item) {
        items.add(item);
        selection.set(items.size() - 1);
    }

    public void sent() {
        newMessage(new ChatMessage(getNextChatId(), ChatMessage.UserType.ME, input.get().trim()));
        input.set("");
    }

    private int getNextChatId() {
        return ++chatId;
    }

    @Override
    public void onDestroy() {

    }
}
