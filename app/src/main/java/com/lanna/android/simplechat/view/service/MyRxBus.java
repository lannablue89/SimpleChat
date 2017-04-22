package com.lanna.android.simplechat.view.service;


import com.lanna.android.simplechat.model.ChatMessage;

import java.util.List;

import rx.subjects.PublishSubject;


/**
 * Created by lanna on 4/22/17.
 */

public class MyRxBus {
    private static MyRxBus instance;

    private PublishSubject<List<ChatMessage>> subject = PublishSubject.create();

    public static MyRxBus getInstance() {
        if (instance == null) {
            instance = new MyRxBus();
        }
        return instance;
    }

    /**
     * Pass any event down to event listeners.
     */
    public void notifyData(List<ChatMessage> object) {
        subject.onNext(object);
    }

    /**
     * Subscribe to this Observable. On event, do something
     * e.g. replace a fragment
     */
    public PublishSubject<List<ChatMessage>> getEvents() {
        return subject;
    }
}
