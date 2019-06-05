package com.undefinedlabs.scope.controller;

import com.undefinedlabs.scope.model.dto.CarLocationDTO;
import com.undefinedlabs.scope.service.CarLocationService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarLocationControllerTest {

    private static final String SAMPLE_UUID = "sampleUUID";

    @Test
    public void should_return_car_location_from_service() {
        //Given
        final CarLocationDTO mockCarLocation = mock(CarLocationDTO.class);
        final CarLocationService mockService = mock(CarLocationService.class);
        when(mockService.getFromRemote(SAMPLE_UUID)).thenReturn(mockCarLocation);

        final CarLocationController sut = new CarLocationController(mockService);

        //When
        final CarLocationDTO result = sut.getByUuid(SAMPLE_UUID);

        //Then
        assertThat(result).isEqualTo(mockCarLocation);
    }
}
