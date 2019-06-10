package com.undefinedlabs.scope.service;

import com.undefinedlabs.scope.db.h2.repository.H2CarLocationRepository;
import com.undefinedlabs.scope.db.mysql.repository.MySQLCarLocationRepository;
import com.undefinedlabs.scope.model.dto.CarLocationDTO;
import com.undefinedlabs.scope.model.entity.CarLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class CarLocationService {

    private static final String CAR_LOCATION_URL = "http://flask-example-project.codescope.com:8000/car/";

    private final RestTemplate restTemplate;

    private final H2CarLocationRepository h2CarLocationRepository;
    private final MySQLCarLocationRepository mySqlCarLocationRepository;

    @Autowired
    public CarLocationService(final RestTemplate restTemplate, final H2CarLocationRepository h2CarLocationRepository, final MySQLCarLocationRepository mySQLCarLocationRepository) {
        this.restTemplate = restTemplate;
        this.h2CarLocationRepository = h2CarLocationRepository;
        this.mySqlCarLocationRepository = mySQLCarLocationRepository;
    }

    public CarLocationDTO getFromRemote(final String uuid){
        return restTemplate.getForEntity(CAR_LOCATION_URL + uuid, CarLocationDTO.class).getBody();
    }

    @Transactional
    public List<CarLocationDTO> saveAllDB(final CarLocationDTO carLocation) {
        final CarLocation entity = new CarLocation(carLocation.getUuid(), carLocation.getLatitude(), carLocation.getLongitude());
        final CarLocation h2EntitySaved = h2CarLocationRepository.save(entity);
        final CarLocation mySqlEntitySaved = mySqlCarLocationRepository.save(entity);

        return Arrays.asList(
                new CarLocationDTO(h2EntitySaved.getUuid(), h2EntitySaved.getLatitude(), h2EntitySaved.getLongitude()),
                new CarLocationDTO(mySqlEntitySaved.getUuid(), mySqlEntitySaved.getLatitude(), mySqlEntitySaved.getLongitude())
        );
    }

    public List<CarLocationDTO> findByUuidAllDB(final String uuid) {
        final CarLocation h2CarLocationByUuid = this.h2CarLocationRepository.findByUuid(uuid).orElse(new CarLocation("",0.0,0.0));
        final CarLocation mySqlCarLocationByUuid = this.mySqlCarLocationRepository.findByUuid(uuid).orElse(new CarLocation("",0.0,0.0));
        return Arrays.asList(
                new CarLocationDTO(h2CarLocationByUuid.getUuid(), h2CarLocationByUuid.getLatitude(), h2CarLocationByUuid.getLatitude()),
                new CarLocationDTO(mySqlCarLocationByUuid.getUuid(), mySqlCarLocationByUuid.getLatitude(), mySqlCarLocationByUuid.getLatitude())
        );
    }

}
