package com.study.andriod.project5.정류소;

public class StList_Item {

    private String busName;
    private String arsId;
    private String stNum;


    public StList_Item(String busName,String arsId,String stNum){

        this.busName = busName;
        this.arsId = arsId;
        this.stNum = stNum;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusRouteId() {
        return arsId;
    }

    public void setBusRouteId(String arsId) {
        this.arsId = arsId;
    }

    public String getStNum() {
        return stNum;
    }

    public void setStNum(String stNum) {
        this.stNum = stNum;
    }
}
