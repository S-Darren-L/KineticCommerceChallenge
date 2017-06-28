package com.darren.android.kineticcommercechallenge.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darren on 6/26/2017.
 */

public class Picture implements Parcelable {

    private String thumbnail;
    private String medium;
    private String large;

    protected Picture(Parcel in) {
        thumbnail = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(thumbnail);
        parcel.writeString(medium);
        parcel.writeString(large);
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
