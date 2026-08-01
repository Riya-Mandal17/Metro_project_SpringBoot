package com.metro.backend.service;

import com.metro.backend.algorithm.Dijkstra;
import com.metro.backend.dto.RouteResponse;


import org.springframework.stereotype.Service;


@Service
public class RouteService {

    private final Dijkstra dijkstra;

    public RouteService(Dijkstra dijkstra){
        this.dijkstra = dijkstra;
    }


    public RouteResponse routeDetails(String source, String destination){
        return dijkstra.findShortestPath(source, destination);
    }
}
