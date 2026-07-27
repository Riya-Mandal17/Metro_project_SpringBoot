package com.metro.backend.controller;

import com.metro.backend.entity.sqlite.Station;
import com.metro.backend.service.StationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService){
        this.stationService = stationService;
    }

    @GetMapping("/stations")
    public List<Station> allStation(){
       return stationService.getAllStations();
    }


}
