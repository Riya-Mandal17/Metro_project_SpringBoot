package com.metro.backend.algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import com.metro.backend.cache.MetroDataCache;
import com.metro.backend.dto.Node;
import com.metro.backend.dto.RouteResponse;
import com.metro.backend.graph.Edge;

@Component
public class Dijkstra {
    private final MetroDataCache metroDataCache;

    public Dijkstra(MetroDataCache metroDataCache){
        this.metroDataCache = metroDataCache;
    }

    public RouteResponse findShortestPath(String source, String destination){
        List<Integer> sourceStationIds = metroDataCache.getStationIdByName(source);
        List<Integer> destinationStationIds = metroDataCache.getStationIdByName(destination);

        
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getTime));
        
        Map<Integer, Integer> distance = new HashMap<>();
        Map<Integer, Integer> routeDetails = new HashMap<>();

        for (Integer sourceId : sourceStationIds){
            distance.put(sourceId, 0);
            
            pq.offer(new Node(sourceId, 0));
            
            while(!pq.isEmpty()){
                Node current = pq.poll();
                List<Edge> neighbours = metroDataCache.getNeighbours(current.getStationId());

                for(Edge edge : neighbours){
                    int newTime = current.getTime() + edge.getTravelTime();
                    if(newTime < distance.getOrDefault(edge.getDestinationStationId(), Integer.MAX_VALUE)){
                        distance.put(edge.getDestinationStationId(), newTime);
                        pq.offer(new Node(edge.getDestinationStationId(), newTime));
                        routeDetails.put(current.getStationId(), edge.getDestinationStationId());
                    }
                }
            }

            

        }

        return new RouteResponse();
    }
}
