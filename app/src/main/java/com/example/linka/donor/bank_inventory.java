package com.example.linka.donor;

/**
 * Created by linka on 13-03-2018.
 */

public class bank_inventory {
    int a_positive;
    int a_negative;
    int b_positive;
    int b_negative;
    int ab_positive;
    int ab_negative;
    int o_positive;
    int o_negative;
    int bombay;

    public bank_inventory(){

    }

    public bank_inventory(int a_positive, int a_negative, int b_positive, int b_negative, int ab_positive, int ab_negative, int o_positive, int o_negative, int bombay) {
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

    public int getA_positive() {return a_positive;}

    public int getA_negative() {
        return a_negative;
    }

    public int getB_positive() {
        return b_positive;
    }

    public int getB_negative() {
        return b_negative;
    }

    public int getAb_positive() {
        return ab_positive;
    }

    public int getAb_negative() {
        return ab_negative;
    }

    public int getO_positive() {
        return o_positive;
    }

    public int getO_negative() {
        return o_negative;
    }

    public int getBombay() {
        return bombay;
    }
}
