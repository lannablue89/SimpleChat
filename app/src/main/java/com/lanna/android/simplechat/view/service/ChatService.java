package com.lanna.android.simplechat.view.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.text.TextUtils;

import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.model.ChatServiceModel;
import com.lanna.android.simplechat.util.LogUtils;

import rx.Observer;

/**
 * Created by lanna on 4/22/17.
 */

public class ChatService extends Service {

    private IBinder mBinder;
    private int chatId = 0;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        LogUtils.v(this, "onStartCommand: ");
        startChatFlow();
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
//        LogUtils.v(this, "onBind: ");
        return mBinder;
    }

    private int getNextChatId() {
        return ++chatId;
    }

    public void startChatFlow() {
        LogUtils.v(this, "startChatFlow: ");
        new ChatServiceModel(chatId).startNormalMessageFlow(new Observer<String>() {

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) {
                LogUtils.w("onError: " + e.getMessage());
            }

            @Override
            public void onNext(String value) {
//                LogUtils.d("onNext: " + value);
                if (!TextUtils.isEmpty(value)) {
                    notifyNewMessage(new ChatMessage(getNextChatId(), ChatMessage.UserType.OTHER, "other", value, Color.GREEN));
                }
            }
        });
    }

    private void notifyNewMessage(ChatMessage other) {
        LogUtils.d(this, "notifyNewMessage: " + other);
        MyRxBus.getInstance().notifyMessage(other);
    }

}