package com.lanna.android.simplechat.model;

import android.support.annotation.IntDef;

/**
 * Created by lanna on 4/19/17.
 */

public class ChatMessage {

    @IntDef({UserType.ME, UserType.OTHER})
    public @interface UserType {
        int ME = 0;
        int OTHER = 1;
    }

    private int id;
    private @UserType int userType;
    private String message;

    public ChatMessage(int id, @UserType int userType, String message) {
        this.id = id;
        this.userType = userType;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public @UserType int getUserType() {
        return userType;
    }

    public String getMessage() {
        return message;
    }
}
