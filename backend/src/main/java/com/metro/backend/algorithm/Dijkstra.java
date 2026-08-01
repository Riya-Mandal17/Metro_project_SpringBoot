package com.metro.backend.algorithm;

import java.util.List;

import org.springframework.stereotype.Component;

import com.metro.backend.cache.MetroDataCache;
import com.metro.backend.dto.RouteResponse;

@Component
public class Dijkstra {
    private final MetroDataCache metroDataCache;

    public Dijkstra(MetroDataCache metroDataCache){
        this.metroDataCache = metroDataCache;
    }

    public RouteResponse findShortestPath(String source, String destination){
        List<Integer> sourceStationIds = metroDataCache.getStationIdByName(source);
        List<Integer> destinationStationIds = metroDataCache.getStationIdByName(destination);

        for (Integer sourceId : sourceStationIds){
            
        }

        return new RouteResponse();
    }
}
