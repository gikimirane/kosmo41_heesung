package com.study.andriod.project6.버스번호;

public class busN_Item {

    private String busRouteId;
    private String busRouteNm;
    private String edStationNm;
    private String stStationNm;




    public busN_Item(String busRouteId, String busRouteNm, String edStationNm, String stStationNm){
        this.busRouteId = busRouteId;
        this.busRouteNm = busRouteNm;
        this.edStationNm = edStationNm;
        this.stStationNm = stStationNm;

    }

    public String getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(String busRouteId) {
        this.busRouteId = busRouteId;
    }

    public String getBusRouteNm() {
        return busRouteNm;
    }

    public void setBusRouteNm(String busRouteNm) {
        this.busRouteNm = busRouteNm;
    }

    public String getEdStationNm() {
        return edStationNm;
    }

    public void setEdStationNm(String edStationNm) {
        this.edStationNm = edStationNm;
    }

    public String getStStationNm() {
        return stStationNm;
    }

    public void setStStationNm(String stStationNm) {
        this.stStationNm = stStationNm;
    }

}
