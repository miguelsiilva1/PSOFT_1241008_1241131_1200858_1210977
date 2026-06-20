package com.marslps.AISafeFMS.application.dto;

public class AirportStatisticsDTO {
    public String iata;
    public int total_routes;

    public AirportStatisticsDTO(String iata, int total_routes) {
        this.iata = iata;
        this.total_routes = total_routes;
    }
}