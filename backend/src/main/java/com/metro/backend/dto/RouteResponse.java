package com.metro.backend.dto;

import java.util.List;

public class RouteResponse {
    /*{
        "route_summary": {
            "source": str,
            "destination": str,
            "total_travel_time_minutes": int,
            "total_fare_inr": int,
            "interchanges_count": int
        },
        "ordered_itinerary": [
            {
                "station_name": str,
                "line": str,
                "is_interchange": bool,
                "transfer_to": str | None
            },
            ...
        ]
    }*/

    private String source;
    private String destination;
    private int total_travel_time_minutes;
    private int total_fare_inr;
    private int interchanges_count;
    List<Itinerary> ordered_itinerary;


    public RouteResponse() {}

    // Parameterized Constructor
    public RouteResponse(String source, String destination,
                        int total_travel_time_minutes,
                        int total_fare_inr,
                        int interchanges_count,
                        List<Itinerary> ordered_itinerary) {
        this.source = source;
        this.destination = destination;
        this.total_travel_time_minutes = total_travel_time_minutes;
        this.total_fare_inr = total_fare_inr;
        this.interchanges_count = interchanges_count;
        this.ordered_itinerary = ordered_itinerary;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotal_travel_time_minutes() {
        return total_travel_time_minutes;
    }

    public void setTotal_travel_time_minutes(int total_travel_time_minutes) {
        this.total_travel_time_minutes = total_travel_time_minutes;
    }

    public int getTotal_fare_inr() {
        return total_fare_inr;
    }

    public void setTotal_fare_inr(int total_fare_inr) {
        this.total_fare_inr = total_fare_inr;
    }

    public int getInterchanges_count() {
        return interchanges_count;
    }

    public void setInterchanges_count(int interchanges_count) {
        this.interchanges_count = interchanges_count;
    }

    public List<Itinerary> getOrdered_itinerary() {
        return ordered_itinerary;
    }

    public void setOrdered_itinerary(List<Itinerary> ordered_itinerary) {
        this.ordered_itinerary = ordered_itinerary;
    }
}
