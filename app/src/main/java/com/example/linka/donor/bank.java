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
    bank_inventory inventory;

    public bank(){

    }

    public bank(String bname, String add_l, String city_l, String district_l, String state_l, bank_inventory inventory) {
        this.bname = bname;
        this.add_l = add_l;
        this.city_l = city_l;
        this.district_l = district_l;
        this.state_l = state_l;
        this.inventory = inventory;
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

    public bank_inventory getInventory() {
        return inventory;
    }
}
