package com.undefinedlabs.scope.repository;

import com.undefinedlabs.scope.model.entity.CarLocation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarLocationRepository extends CrudRepository<CarLocation, Long> {

    Optional<CarLocation> findByUuid(final String uuid);
}
