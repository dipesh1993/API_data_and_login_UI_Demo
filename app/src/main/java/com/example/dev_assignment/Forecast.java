package com.example.dev_assignment;

public class Forecast {

    private long dt;
    private double pressure;
    private String humidity;

    public Forecast( long dt, double pressure, String humidity) {

        this.dt = dt;
//        this.city = city;
        this.pressure = pressure;
        this.humidity = humidity;
    }


//    public String getCity() {
//        return city;
//    }

    public Long getDate() {
        return dt;
    }

    public Double getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

}