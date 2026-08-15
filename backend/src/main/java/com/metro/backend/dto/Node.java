package com.metro.backend.dto;

public class Node {
    private Integer stationId;
    private Integer time;


    public Node(Integer stationId, Integer time) {
        this.stationId = stationId;
        this.time = time;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
