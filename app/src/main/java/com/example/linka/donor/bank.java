package com.example.linka.donor;

/**
 * Created by linka on 13-03-2018.
 */

public class bank {
    String bname;
    String add_l;
    String city_l;
    String district_l;
    String state_l;
    String a_positive;
    String a_negative;
    String b_positive;
    String b_negative;
    String ab_positive;
    String ab_negative;
    String o_positive;
    String o_negative;
    String bombay;

    public bank(){

    }

    public bank(String bname, String add_l, String city_l, String district_l, String state_l, String a_positive, String a_negative, String b_positive, String b_negative, String ab_positive, String ab_negative, String o_positive, String o_negative, String bombay) {
        this.bname = bname;
        this.add_l = add_l;
        this.city_l = city_l;
        this.district_l = district_l;
        this.state_l = state_l;
        this.a_positive = a_positive;
        this.a_negative = a_negative;
        this.b_positive = b_positive;
        this.b_negative = b_negative;
        this.ab_positive = ab_positive;
        this.ab_negative = ab_negative;
        this.o_positive = o_positive;
        this.o_negative = o_negative;
        this.bombay = bombay;
    }

    public String getBname() {
        return bname;
    }

    public String getAdd_l() {
        return add_l;
    }

    public String getCity_l() {
        return city_l;
    }

    public String getDistrict_l() {
        return district_l;
    }

    public String getState_l() {
        return state_l;
    }

    public String getA_positive() {
        return a_positive;
    }

    public String getA_negative() {
        return a_negative;
    }

    public String getB_positive() {
        return b_positive;
    }

    public String getB_negative() {
        return b_negative;
    }

    public String getAb_positive() {
        return ab_positive;
    }

    public String getAb_negative() {
        return ab_negative;
    }

    public String getO_positive() {
        return o_positive;
    }

    public String getO_negative() {
        return o_negative;
    }

    public String getBombay() {
        return bombay;
    }
}
