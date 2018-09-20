package com.study.andriod.list6;

public class SingerItem {

    private String name;
    private String Telnum;
    private int resId;

    public SingerItem(String name, String Telnum, int resId) {
        this.name = name;
        this.Telnum = Telnum;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelnum() {
        return Telnum;
    }

    public void setTelnum(String Telnum) {
        this.Telnum = Telnum;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
