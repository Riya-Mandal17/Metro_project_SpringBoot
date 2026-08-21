package com.metro.backend.graph;

public class Edge {

    private final int destinationStationId;
    private final int travelTime;
    private final int fare;
    private final boolean interchange;

    public Edge(
            int destinationStationId,
            int travelTime,
            int fare,
            boolean interchange) {

        this.destinationStationId = destinationStationId;
        this.travelTime = travelTime;
        this.fare = fare;
        this.interchange = interchange;
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

    public boolean isInterchange() {
        return interchange;
    }
}