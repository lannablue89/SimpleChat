package com.lanna.android.simplechat.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

/**
 * Created by lanna on 4/19/17.
 */

public class ChatMessage implements Parcelable {

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

    @Override
    public String toString() {
        return "ChatMessage: {id=" + id
                + ", userType=" + userType
                + ", name=" + name
                + ", message=" + message
                +"}";
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


    ///////////////////////////////////////////////////////////////////////////
    // Parcelable
    ///////////////////////////////////////////////////////////////////////////

    protected ChatMessage(Parcel in) {
        id = in.readInt();
        setUserType(in.readInt());
        name = in.readString();
        message = in.readString();
        color = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userType);
        dest.writeString(name);
        dest.writeString(message);
        dest.writeInt(color);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ChatMessage> CREATOR = new Parcelable.Creator<ChatMessage>() {
        @Override
        public ChatMessage createFromParcel(Parcel in) {
            return new ChatMessage(in);
        }

        @Override
        public ChatMessage[] newArray(int size) {
            return new ChatMessage[size];
        }
    };
    // end of Parcelable
}
