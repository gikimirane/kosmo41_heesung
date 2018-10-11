package com.study.andriod.project6.노선;

public class bRid_Item {

    private String arsId;
    private String beginTm;
    private String lastTm;
    private String stationNm;
    private String seq;
    private String equls;


    public bRid_Item(String arsId,String beginTm,String lastTm,String stationNm,String seq,String equls){

        this.arsId = arsId;
        this.beginTm = beginTm;
        this.lastTm = lastTm;
        this.stationNm = stationNm;
        this.seq = seq;
        this.equls = equls;
    }

    public String getArsId() {
        return arsId;
    }

    public void setArsId(String arsId) {
        this.arsId = arsId;
    }

    public String getBeginTm() {
        return beginTm;
    }

    public void setBeginTm(String beginTm) {
        this.beginTm = beginTm;
    }

    public String getLastTm() {
        return lastTm;
    }

    public void setLastTm(String lastTm) {
        this.lastTm = lastTm;
    }

    public String getStationNm() {
        return stationNm;
    }

    public void setStationNm(String stationNm) {
        this.stationNm = stationNm;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getEquls() {
        return equls;
    }

    public void setEquls(String equls) {
        this.equls = equls;
    }
}
