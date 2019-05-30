package com.undefinedlabs.scope.service;

import com.undefinedlabs.scope.model.CarLocation;
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
        final CarLocation mockCarLocation = mock(CarLocation.class);
        final ResponseEntity<CarLocation> mockResponseEntity = mock(ResponseEntity.class);
        when(mockResponseEntity.getBody()).thenReturn(mockCarLocation);
        final RestTemplate mockRestTemplate = mock(RestTemplate.class);
        when(mockRestTemplate.getForEntity(anyString(), eq(CarLocation.class))).thenReturn(mockResponseEntity);
        final CarLocationService sut = new CarLocationService(mockRestTemplate);

        //When
        final CarLocation result = sut.findById(SAMPLE_UUID);

        //Then
        assertThat(result).isEqualTo(mockCarLocation);
    }
}
