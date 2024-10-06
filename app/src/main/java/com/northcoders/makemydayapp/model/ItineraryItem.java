package com.northcoders.makemydayapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ItineraryItem<T extends DisplaybleItem> implements Parcelable {

    T value;

    public ItineraryItem(T value){
        this.value = value;
    }

    protected ItineraryItem(Parcel in) {
        value = in.readParcelable(getClass().getClassLoader());
    }

    public static final Creator<ItineraryItem> CREATOR = new Creator<ItineraryItem>() {
        @Override
        public ItineraryItem createFromParcel(Parcel in) {
            return new ItineraryItem(in);
        }

        @Override
        public ItineraryItem[] newArray(int size) {
            return new ItineraryItem[size];
        }
    };

    public String getDisplay(){
        return value.display();
    }

    public Class<?> getValueClass(){
        return value.getClass();
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ItineraryItem{" +
                "value=" + value +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flag) {
        parcel.writeParcelable(value, flag);
    }
}
