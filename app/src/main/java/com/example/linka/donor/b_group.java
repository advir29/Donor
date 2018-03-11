package com.example.linka.donor;

/**
 * Created by linka on 11-03-2018.
 */

public class b_group {
    String f_name;
    String l_name;
    int ht;
    int wt;
    int age;
    String gender;
    String b_group;

    public  b_group(){

    }

    public b_group(String f_name, String l_name, int ht, int wt, int age, String gender, String b_group) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.ht = ht;
        this.wt = wt;
        this.age = age;
        this.gender = gender;
        this.b_group = b_group;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public int getHt() {
        return ht;
    }

    public int getWt() {
        return wt;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getB_group() {
        return b_group;
    }
}
