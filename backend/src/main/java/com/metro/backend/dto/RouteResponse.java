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
    private String detination;
    private int total_travel_time_minutes;
    private int total_fare_inr;
    private int interchanges_count;
    List<Itinerary> ordered_itinerary;
}
