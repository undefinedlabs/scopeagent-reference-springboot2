package com.undefinedlabs.scope.db.h2.repository;

import com.undefinedlabs.scope.model.entity.CarLocation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface H2CarLocationRepository extends CrudRepository<CarLocation, Long> {

    Optional<CarLocation> findByUuid(final String uuid);
}
