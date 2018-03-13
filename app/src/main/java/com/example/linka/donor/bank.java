package com.example.linka.donor;

/**
 * Created by linka on 13-03-2018.
 */

public class bank {
    String bank_name;
    String add1;
    String city_name;
    String district_name;
    String state_name;

    public bank(){

    }

    public bank(String bank_name, String add1, String city_name, String district_name, String state_name) {
        this.bank_name = bank_name;
        this.add1 = add1;
        this.city_name = city_name;
        this.district_name = district_name;
        this.state_name = state_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getAdd1() {
        return add1;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public String getState_name() {
        return state_name;
    }
}
