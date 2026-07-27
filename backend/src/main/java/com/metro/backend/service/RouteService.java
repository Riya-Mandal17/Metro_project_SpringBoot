package com.metro.backend.service;

import com.metro.backend.entity.sqlite.Connections;
import com.metro.backend.repository.sqlite.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    public RouteService(RouteRepository routeRepository){
        this.routeRepository = routeRepository;
    }

    public List<Connections> routeDetails(String source, String destination){
        return routeRepository.getConnections();
    }



}
