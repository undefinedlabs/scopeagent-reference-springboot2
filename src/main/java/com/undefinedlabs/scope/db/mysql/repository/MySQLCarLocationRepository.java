package com.undefinedlabs.scope.db.mysql.repository;

import com.undefinedlabs.scope.model.entity.CarLocation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MySQLCarLocationRepository extends CrudRepository<CarLocation, Long> {

    Optional<CarLocation> findByUuid(final String uuid);
}
