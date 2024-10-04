package com.northcoders.makemydayapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MMDEvent implements Parcelable {

    private String eventName;

    public MMDEvent(String eventName) {
        this.eventName = eventName;
    }

    protected MMDEvent(Parcel in) {
        eventName = in.readString();
    }

    public static final Creator<MMDEvent> CREATOR = new Creator<MMDEvent>() {
        @Override
        public MMDEvent createFromParcel(Parcel in) {
            return new MMDEvent(in);
        }

        @Override
        public MMDEvent[] newArray(int size) {
            return new MMDEvent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(eventName);
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
