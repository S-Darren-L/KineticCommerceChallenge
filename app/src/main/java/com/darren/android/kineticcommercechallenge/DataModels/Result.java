package com.darren.android.kineticcommercechallenge.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darren on 6/26/2017.
 */

public class Result implements Parcelable {

    private Picture picture;
    private Id id;
    private String phone;
    private String email;
    private Location location;
    private String registered;
    private String cell;
    private String dob;
    private Name name;
    private String gender;
    private String nat;
    private Login login;

    protected Result(Parcel in) {
        picture = in.readParcelable(Picture.class.getClassLoader());
        id = in.readParcelable(Id.class.getClassLoader());
        phone = in.readString();
        email = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        registered = in.readString();
        cell = in.readString();
        dob = in.readString();
        name = in.readParcelable(Name.class.getClassLoader());
        gender = in.readString();
        nat = in.readString();
        login = in.readParcelable(Login.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(picture, flags);
        dest.writeParcelable(id, flags);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeParcelable(location, flags);
        dest.writeString(registered);
        dest.writeString(cell);
        dest.writeString(dob);
        dest.writeParcelable(name, flags);
        dest.writeString(gender);
        dest.writeString(nat);
        dest.writeParcelable(login, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
