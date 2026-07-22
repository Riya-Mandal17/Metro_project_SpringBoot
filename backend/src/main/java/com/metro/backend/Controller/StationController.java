package com.metro.backend.Controller;

import com.metro.backend.Entity.Station;
import com.metro.backend.Service.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {

    @GetMapping("/stations")
    public void allStation(){
       private List<Station> stionsDetails;
        StationController(StationService stationService){
            stionsDetails = this.stationService ;
        }
    }


}
