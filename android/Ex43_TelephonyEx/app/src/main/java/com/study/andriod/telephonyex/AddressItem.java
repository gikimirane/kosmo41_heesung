package com.study.andriod.telephonyex;

import android.graphics.Bitmap;

public class AddressItem {

    private String name;
    private String Telnum;
    private Bitmap photo;

    public AddressItem(String name, String Telnum, Bitmap photo) {
        this.name = name;
        this.Telnum = Telnum;
        this.photo = photo;
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

    public Bitmap getResId() {
        return photo;
    }

    public void setResId(Bitmap photo) {
        this.photo = photo;
    }
}
