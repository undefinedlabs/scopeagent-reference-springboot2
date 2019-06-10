package com.undefinedlabs.scope.service;

import com.undefinedlabs.scope.db.h2.repository.H2CarLocationRepository;
import com.undefinedlabs.scope.db.mysql.repository.MySQLCarLocationRepository;
import com.undefinedlabs.scope.model.dto.CarLocationDTO;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarLocationServiceTest {

    private static final String SAMPLE_UUID = "sampleUUID";

    @Test
    public void should_return_car_location_from_remote_call() {
        //Given
        final CarLocationDTO mockCarLocation = mock(CarLocationDTO.class);
        final ResponseEntity<CarLocationDTO> mockResponseEntity = mock(ResponseEntity.class);
        when(mockResponseEntity.getBody()).thenReturn(mockCarLocation);
        final RestTemplate mockRestTemplate = mock(RestTemplate.class);
        final H2CarLocationRepository h2MockCarLocationRepository = mock(H2CarLocationRepository.class);
        final MySQLCarLocationRepository mySQLMockCarLocationRepository = mock(MySQLCarLocationRepository.class);
        when(mockRestTemplate.getForEntity(anyString(), eq(CarLocationDTO.class))).thenReturn(mockResponseEntity);
        final CarLocationService sut = new CarLocationService(mockRestTemplate, h2MockCarLocationRepository, mySQLMockCarLocationRepository);

        //When
        final CarLocationDTO result = sut.getFromRemote(SAMPLE_UUID);

        //Then
        assertThat(result).isEqualTo(mockCarLocation);
    }
}
