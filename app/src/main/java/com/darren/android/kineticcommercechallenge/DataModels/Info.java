package com.darren.android.kineticcommercechallenge.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darren on 6/26/2017.
 */

public class Info implements Parcelable {

    private String results;
    private String page;
    private String seed;
    private String version;

    protected Info(Parcel in) {
        results = in.readString();
        page = in.readString();
        seed = in.readString();
        version = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(results);
        dest.writeString(page);
        dest.writeString(seed);
        dest.writeString(version);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
