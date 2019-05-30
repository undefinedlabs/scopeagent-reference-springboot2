package com.undefinedlabs.scope.service;

import com.undefinedlabs.scope.model.CarLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CarLocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarLocationService.class);
    private static final String CAR_LOCATION_URL = "http://flask-example-project.codescope.com:8000/car/";

    private final RestTemplate restTemplate;

    @Autowired
    public CarLocationService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CarLocation findById(final String uuid){
        return restTemplate.getForEntity(CAR_LOCATION_URL + uuid, CarLocation.class).getBody();
    }

}
