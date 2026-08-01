package com.metro.backend.dto;

public class Itinerary {
    private String station_name;
    private String line;
    private boolean is_interchange;
    private String transfer_to;

    // Default Constructor
    public Itinerary() {
    }

    // Parameterized Constructor
    public Itinerary(String station_name, String line, boolean is_interchange, String transfer_to) {
        this.station_name = station_name;
        this.line = line;
        this.is_interchange = is_interchange;
        this.transfer_to = transfer_to;
    }

    // Getters and Setters
    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public boolean isIs_interchange() {
        return is_interchange;
    }

    public void setIs_interchange(boolean is_interchange) {
        this.is_interchange = is_interchange;
    }

    public String getTransfer_to() {
        return transfer_to;
    }

    public void setTransfer_to(String transfer_to) {
        this.transfer_to = transfer_to;
    }
}
