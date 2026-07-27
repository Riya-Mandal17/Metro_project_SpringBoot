package com.metro.backend.entity.sqlite;

import jakarta.persistence.*;

@Entity
public class Interchanges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @ManyToOne
    @JoinColumn(name = "station_from_id")
    private Station station_from_id ;

    @ManyToOne
    @JoinColumn(name = "station_to_id")
    private Station station_to_id ;

    private int transfer_time_minutes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Station getStation_from_id() {
        return station_from_id;
    }

    public void setStation_from_id(Station station_from_id) {
        this.station_from_id = station_from_id;
    }

    public Station getStation_to_id() {
        return station_to_id;
    }

    public void setStation_to_id(Station station_to_id) {
        this.station_to_id = station_to_id;
    }

    public int getTransfer_time_minutes() {
        return transfer_time_minutes;
    }

    public void setTransfer_time_minutes(int transfer_time_minutes) {
        this.transfer_time_minutes = transfer_time_minutes;
    }

    @Override
    public String toString() {
        return "Interchanges{" +
                "id=" + id +
                ", station_from_id=" + station_from_id +
                ", station_to_id=" + station_to_id +
                ", transfer_time_minutes=" + transfer_time_minutes +
                '}';
    }
}
