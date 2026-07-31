package com.metro.backend.graph;

public class Edge {

    private final int destinationStationId;
    private final int travelTime;
    private final int fare;
    private final boolean isInterchange;

    public Edge(int destinationStationId,
                int travelTime,
                int fare, boolean isInterchange) {

        this.destinationStationId = destinationStationId;
        this.travelTime = travelTime;
        this.fare = fare;
        this.isInterchange = isInterchange;
    }

    public int getDestinationStationId() {
        return destinationStationId;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public int getFare() {
        return fare;
    }

    public boolean getIsInterchange(){
        return this.isInterchange;
    }
}