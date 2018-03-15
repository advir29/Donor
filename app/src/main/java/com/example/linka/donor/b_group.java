package com.example.linka.donor;

/**
 * Created by linka on 11-03-2018.
 */

public class b_group {
    String f_name;
    String l_name;
    String ht;
    String wt;
    String age;
    String gender;
    String b_group;
    String units;

    public  b_group(){

    }

    public b_group(String f_name, String l_name, String ht, String wt, String age, String gender, String b_group, String units) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.ht = ht;
        this.wt = wt;
        this.age = age;
        this.gender = gender;
        this.b_group = b_group;
        this.units = units;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getHt() {
        return ht;
    }

    public String getWt() {
        return wt;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getB_group() {
        return b_group;
    }

    public String getUnits() {
        return units;
    }
}
