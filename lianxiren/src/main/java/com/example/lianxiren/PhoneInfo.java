package com.example.lianxiren;

/**
 * Created by xuYang on 2017/8/24.
 */

public class PhoneInfo {
    private String name;
    private String number;

    public PhoneInfo(String name,String number) {
        setNumber(number);
        setName(name);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }


}
