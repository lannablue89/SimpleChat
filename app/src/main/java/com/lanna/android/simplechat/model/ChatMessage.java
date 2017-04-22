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
    private String name;
    private String message;

    private int color;

    public ChatMessage() {}

    public ChatMessage(int id, @UserType int userType, String name, String message, int color) {
        this.id = id;
        this.userType = userType;
        this.name = name;
        this.message = message;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public @UserType int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public int getColor() {
        return color;
    }
}
