package com.metro.backend.entity.sqlite;

import jakarta.persistence.*;

@Entity
public class Connections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @ManyToOne
    @JoinColumn(name = "station_a_id")
    private Station station_a_id;

    @ManyToOne
    @JoinColumn(name = "station_b_id")
    private Station station_b_id;

    private int travel_time_minutes;
    private int fare_inr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Station getStation_a_id() {
        return station_a_id;
    }

    public void setStation_a_id(Station station_a_id) {
        this.station_a_id = station_a_id;
    }

    public Station getStation_b_id() {
        return station_b_id;
    }

    public void setStation_b_id(Station station_b_id) {
        this.station_b_id = station_b_id;
    }

    public int getTravel_time_minutes() {
        return travel_time_minutes;
    }

    public void setTravel_time_minutes(int travel_time_minutes) {
        this.travel_time_minutes = travel_time_minutes;
    }

    public int getFare_inr() {
        return fare_inr;
    }

    public void setFare_inr(int fare_inr) {
        this.fare_inr = fare_inr;
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
