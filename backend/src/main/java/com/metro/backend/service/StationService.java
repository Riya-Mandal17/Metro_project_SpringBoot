package com.metro.backend.service;

import org.springframework.stereotype.Service;
import com.metro.backend.cache.MetroDataCache;
import com.metro.backend.entity.sqlite.Station;

import java.util.List;

@Service
public class StationService {

    private final MetroDataCache cacheData;

    public StationService(MetroDataCache cacheData){
        this.cacheData = cacheData;
    }
    public List<Station> getAllStations(){
        return cacheData.getAllStations();
    }
}
