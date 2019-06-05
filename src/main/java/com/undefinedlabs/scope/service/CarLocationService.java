package com.undefinedlabs.scope.service;

import com.undefinedlabs.scope.model.dto.CarLocationDTO;
import com.undefinedlabs.scope.model.entity.CarLocation;
import com.undefinedlabs.scope.repository.CarLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class CarLocationService {

    private static final String CAR_LOCATION_URL = "http://flask-example-project.codescope.com:8000/car/";

    private final RestTemplate restTemplate;
    private final CarLocationRepository carLocationRepository;

    @Autowired
    public CarLocationService(final RestTemplate restTemplate, final CarLocationRepository carLocationRepository) {
        this.restTemplate = restTemplate;
        this.carLocationRepository = carLocationRepository;
    }

    public CarLocationDTO getFromRemote(final String uuid){
        return restTemplate.getForEntity(CAR_LOCATION_URL + uuid, CarLocationDTO.class).getBody();
    }

    @Transactional
    public CarLocationDTO save(final CarLocationDTO carLocation) {
        final CarLocation saved = carLocationRepository.save(new CarLocation(carLocation.getUuid(), carLocation.getLatitude(), carLocation.getLongitude()));
        return new CarLocationDTO(saved.getUuid(), saved.getLatitude(), saved.getLongitude());
    }

    public CarLocationDTO findByUuid(final String uuid) {
        final CarLocation carLocationByUuid = this.carLocationRepository.findByUuid(uuid).orElse(new CarLocation("",0.0,0.0));
        return new CarLocationDTO(carLocationByUuid.getUuid(), carLocationByUuid.getLatitude(), carLocationByUuid.getLatitude());
    }

}
