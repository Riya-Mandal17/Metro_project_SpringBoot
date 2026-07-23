package com.metro.backend.service;

import org.springframework.stereotype.Service;

import com.metro.backend.entity.Station;
import com.metro.backend.repository.StationRepository;

import java.util.List;

@Service
public class StationService {

    private final StationRepository repository;

    public StationService(StationRepository repository){
        this.repository = repository;
    }
    public List<Station> getAllStations(){
        return repository.findAll();
    }
}
