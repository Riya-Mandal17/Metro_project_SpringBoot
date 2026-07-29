package com.metro.backend.service;

import com.metro.backend.entity.sqlite.Connections;
import com.metro.backend.entity.sqlite.Interchanges;
import com.metro.backend.entity.sqlite.Station;
import com.metro.backend.repository.sqlite.ConnectionRepository;
import com.metro.backend.repository.sqlite.InterchangeRepository;
import com.metro.backend.repository.sqlite.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final StationRepository stationRepository;
    private final ConnectionRepository connectionRepository;
    private final InterchangeRepository interchangeRepository;

    public RouteService(StationRepository stationRepository,ConnectionRepository connectionRepository,InterchangeRepository interchangeRepository){
        this.stationRepository = stationRepository;
        this.connectionRepository = connectionRepository;
        this.interchangeRepository = interchangeRepository;
    }

    public void localData(){
        List<Station> stations = stationRepository.findAll();
        List<Connections> connections = connectionRepository.findAll();
        List<Interchanges> interchanges = interchangeRepository.findAll();


    }



}
