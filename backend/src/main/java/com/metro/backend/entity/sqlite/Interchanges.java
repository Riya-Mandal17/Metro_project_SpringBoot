package com.metro.backend.entity.sqlite;

import jakarta.persistence.*;

@Entity
public class Interchanges {

    @Id
    private int id ;
    private int station_from_id ;
    private int station_to_id ;
    private int transfer_time_minutes;

    public int getId() {
        return id;
    }

    public int getStation_from_id() {
        return station_from_id;
    }

    public int getStation_to_id() {
        return station_to_id;
    }

    public int getTransfer_time_minutes() {
        return transfer_time_minutes;
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
