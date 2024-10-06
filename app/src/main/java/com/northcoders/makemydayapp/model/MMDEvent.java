package com.northcoders.makemydayapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MMDEvent implements Parcelable, DisplaybleItem {

    private String id;
    private String name;
    private String description;
    private String price;
    private String date;
    private String startTime;
    private String endTime;
    private String resourceType;
    private String activityType;


    protected MMDEvent(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readString();
        resourceType = in.readString();
        activityType = in.readString();
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
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(resourceType);
        dest.writeString(activityType);
    }

    @Override
    public String display() {
        return String.format("%s - %s (%s %s-%s)",
                this.activityType,
                name,
                this.date,
                this.startTime,
                this.endTime
        );
    }

//    @Override
//    public String display() {
//        return String.format("%s - %s (%s %s-%s)",
//                this.activityType,
//                name,
//                this.date.format(DateTimeFormatter.ISO_LOCAL_DATE),
//                this.startTime.format(DateTimeFormatter.ISO_LOCAL_TIME),
//                this.endTime.format(DateTimeFormatter.ISO_LOCAL_TIME)
//                );
//    }
}
