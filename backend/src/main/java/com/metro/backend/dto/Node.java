package com.metro.backend.dto;

public class Node {

    private final Integer stationId;
    private final Integer time;

    public Node(Integer stationId, Integer time) {
        this.stationId = stationId;
        this.time = time;
    }

    public Integer getStationId() {
        return stationId;
    }

    public Integer getTime() {
        return time;
    }
}