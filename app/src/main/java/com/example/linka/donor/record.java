package com.example.linka.donor;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by linka on 14-03-2018.
 */

public class record implements Parcelable {
    String bname;
    String address;
    String city;
    String district;
    String state;
    String unit;


    protected record(Parcel in) {
        bname = in.readString();
        address = in.readString();
        city = in.readString();
        district = in.readString();
        state = in.readString();
        unit = in.readString();
    }

    public static final Creator<record> CREATOR = new Creator<record>() {
        @Override
        public record createFromParcel(Parcel in) {
            return new record(in);
        }

        @Override
        public record[] newArray(int size) {
            return new record[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bname);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(district);
        parcel.writeString(state);
        parcel.writeString(unit);
    }

    public record(String bname, String address, String city, String district, String state, String unit) {
        this.bname = bname;
        this.address = address;
        this.city = city;
        this.district = district;
        this.state = state;
        this.unit = unit;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static Creator<record> getCREATOR() {
        return CREATOR;
    }
}

