package com.study.andriod.project5.정류소;

public class St_Item {

    private String fbus;
    private String Lastbus;
    private String Firstbus;
    private String nextStn;
    private String busName;
    private String busRouteId;


    public St_Item(String busName, String fbus,String nextStn, String Lastbus, String Firstbus, String busRouteId){
        this.fbus = fbus;
        this.Lastbus = Lastbus;
        this.Firstbus = Firstbus;
        this.nextStn = nextStn;
        this.busName = busName;
        this.busRouteId = busRouteId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getFbus() {
        return fbus;
    }

    public void setFbus(String fbus) {
        this.fbus = fbus;
    }

    public String getLastbus() {
        return Lastbus;
    }

    public void setLastbus(String lastbus) {
        Lastbus = lastbus;
    }

    public String getFirstbus() {
        return Firstbus;
    }

    public void setFirstbus(String firstbus) {
        Firstbus = firstbus;
    }

    public String getNextStn() {
        return nextStn;
    }

    public void setNextStn(String nextStn) {
        this.nextStn = nextStn;
    }

    public String getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(String busRouteId) {
        this.busRouteId = busRouteId;
    }
}
