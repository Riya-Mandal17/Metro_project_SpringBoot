package com.metro.backend.cache;

import com.metro.backend.entity.sqlite.Connections;
import com.metro.backend.entity.sqlite.Interchanges;
import com.metro.backend.entity.sqlite.Station;
import com.metro.backend.graph.Edge;
import com.metro.backend.repository.sqlite.ConnectionRepository;
import com.metro.backend.repository.sqlite.InterchangeRepository;
import com.metro.backend.repository.sqlite.StationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MetroDataCache {

    private final StationRepository stationRepository;
    private final ConnectionRepository connectionRepository;
    private final InterchangeRepository interchangeRepository;

    private Map<Integer, Station> stationMap;
    private Map<Integer, List<Edge>> graph;
    private List<Station> stations;
    

    public MetroDataCache(StationRepository stationRepository,
                          ConnectionRepository connectionRepository,
                          InterchangeRepository interchangeRepository) {
        this.stationRepository = stationRepository;
        this.connectionRepository = connectionRepository;
        this.interchangeRepository = interchangeRepository;
    }

    @PostConstruct
    public void loadData() {

        // Load data from SQLite
        stations = stationRepository.findAll();
        List<Connections> connections = connectionRepository.findAll();
        List<Interchanges> interchanges = interchangeRepository.findAll();

        // Station lookup
        stationMap = stations.stream()
                .collect(Collectors.toMap(
                        Station::getId,
                        Function.identity()
                ));

    

        // Graph
        graph = new HashMap<>();

        for (Connections connection : connections) {

            graph.computeIfAbsent(
                    connection.getStation_a_id(),
                    k -> new ArrayList<>());

            graph.computeIfAbsent(
                    connection.getStation_b_id(),
                    k -> new ArrayList<>());

            // Source -> Destination
            graph.get(connection.getStation_a_id())
                    .add(new Edge(
                            connection.getStation_b_id(),
                            connection.getTravel_time_minutes(),
                            connection.getFare_inr(),
                            false
                    ));

            // Destination -> Source
            graph.get(connection.getStation_b_id())
                    .add(new Edge(
                            connection.getStation_a_id(),
                            connection.getTravel_time_minutes(),
                            connection.getFare_inr(),
                            false
                    ));
        }

        // Interchange lookup
        for(Interchanges interchange : interchanges){
            graph.computeIfAbsent(
                    interchange.getStation_from_id(),
                    k -> new ArrayList<>());

            graph.computeIfAbsent(
                    interchange.getStation_to_id(),
                    k -> new ArrayList<>());

            // Source -> Destination
            graph.get(interchange.getStation_from_id())
                    .add(new Edge(
                            interchange.getStation_to_id(),
                            interchange.getTransfer_time_minutes(),
                            0,
                            true
                    ));

            // Destination -> Source
            graph.get(interchange.getStation_to_id())
                    .add(new Edge(
                            interchange.getStation_from_id(),
                            interchange.getTransfer_time_minutes(),
                            0,
                            true
                    ));
        }

        
    }

    public Map<Integer, Station> getStationMap() {
        return stationMap;
    }

    public Map<Integer, List<Edge>> getGraph() {
        return graph;
    }


    public Station getStation(Integer stationId) {
        return stationMap.get(stationId);
    }

    public List<Edge> getNeighbours(Integer stationId) {
        return graph.getOrDefault(stationId, Collections.emptyList());
    }

    public List<Station> getAllStations(){
        return this.stations;
    }

}