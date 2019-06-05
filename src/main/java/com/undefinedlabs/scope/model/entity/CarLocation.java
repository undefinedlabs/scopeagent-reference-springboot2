package com.undefinedlabs.scope.model.entity;

import javax.persistence.*;

@Entity
public class CarLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    public CarLocation(){}

    public CarLocation(final String uuid, final double latitude, final double longitude){
        this.uuid = uuid;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
