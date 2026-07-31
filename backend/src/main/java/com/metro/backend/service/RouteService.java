package com.metro.backend.service;

import com.metro.backend.algorithm.Dijkstra;
import com.metro.backend.cache.MetroDataCache;
import com.metro.backend.dto.RouteResponse;
import com.metro.backend.entity.sqlite.Connections;
import com.metro.backend.entity.sqlite.Interchanges;
import com.metro.backend.entity.sqlite.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
