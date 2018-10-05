package com.study.andriod.project3;

public class SingerItem {

    private String fbus;
    private String Lastbus;
    private String Firstbus;
    private String nextStn;
    private String busName;


    public SingerItem(String busName, String fbus,String nextStn, String Lastbus, String Firstbus){
        this.fbus = fbus;
        this.Lastbus = Lastbus;
        this.Firstbus = Firstbus;
        this.nextStn = nextStn;
        this.busName = busName;
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
}
