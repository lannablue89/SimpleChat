package com.lanna.android.simplechat.view.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.text.TextUtils;

import com.lanna.android.simplechat.R;
import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.model.ChatServiceModel;
import com.lanna.android.simplechat.model.NotificationModel;
import com.lanna.android.simplechat.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * Created by lanna on 4/22/17.
 */

public class ChatService extends Service {

    private IBinder mBinder;
    private int chatId;
    private List<ChatMessage> items = new ArrayList<>();
    private NotificationModel notificationModel;
    private int badge;
    private ChatServiceModel chatServiceModel;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationModel = new NotificationModel();
    }

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
        if (chatServiceModel == null) {
            chatServiceModel = new ChatServiceModel();
            chatServiceModel.startNormalMessageFlow(new Observer<String>() {

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
    }

    private void notifyNewMessage(ChatMessage data) {
        LogUtils.d(this, "notifyNewMessage: " + data);
        items.add(data);

        if (MyRxBus.getInstance().getEvents().hasObservers()) {
            badge = 0;
            MyRxBus.getInstance().notifyData(items);
        }
        else {
            badge ++;
            notificationModel.generateNotification(this, getString(R.string.app_name),
                    "You have new " + badge + " messages");
        }
    }

}