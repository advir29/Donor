package com.example.linka.donor;

/**
 * Created by linka on 13-03-2018.
 */

public class transaction {
    String timestamp;
    String fromU;
    String toU;
    String marker;
    String unitsT;

    public transaction(){

    }

    public transaction(String timestamp, String fromU, String toU, String marker, String unitsT) {
        this.timestamp = timestamp;
        this.fromU = fromU;
        this.toU = toU;
        this.marker = marker;
        this.unitsT = unitsT;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFromU() {
        return fromU;
    }

    public void setFromU(String fromU) {
        this.fromU = fromU;
    }

    public String getToU() {
        return toU;
    }

    public void setToU(String toU) {
        this.toU = toU;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getUnitsT() {
        return unitsT;
    }

    public void setUnitsT(String unitsT) {
        this.unitsT = unitsT;
    }
}
