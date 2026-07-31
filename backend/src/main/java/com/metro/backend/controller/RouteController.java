package com.metro.backend.controller;

import com.metro.backend.dto.RouteResponse;
import com.metro.backend.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService){
        this.routeService = routeService;
    }

    @GetMapping("/route")
    public RouteResponse getRouteDetails(@RequestParam("src") String source, @RequestParam("dest") String destination){
        return routeService.routeDetails(source, destination);
    }

}
