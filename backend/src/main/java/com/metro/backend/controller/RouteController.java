package com.metro.backend.controller;

import com.metro.backend.entity.sqlite.Connections;
import com.metro.backend.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService){
        this.routeService = routeService;
    }

    @GetMapping("/route")
    public List<Connections> getRouteDetails(String source, String destination){
        return routeService.routeDetails(source,destination);
    }

}
