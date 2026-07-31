package com.metro.backend.entity.sqlite;

import jakarta.persistence.*;

@Entity
public class Connections {

    @Id
    private int id ;
    private int station_a_id;
    private int station_b_id;
    private int travel_time_minutes;
    private int fare_inr;

    public int getId() {
        return id;
    }

    public int getStation_a_id() {
        return station_a_id;
    }

    public int getStation_b_id() {
        return station_b_id;
    }

    public int getTravel_time_minutes() {
        return travel_time_minutes;
    }

    public int getFare_inr() {
        return fare_inr;
    }

    @Override
    public String toString() {
        return "Connections{" +
                "id=" + id +
                ", station_a_id=" + station_a_id +
                ", station_b_id=" + station_b_id +
                ", travel_time_minutes=" + travel_time_minutes +
                ", fare_inr=" + fare_inr +
                '}';
    }
}
