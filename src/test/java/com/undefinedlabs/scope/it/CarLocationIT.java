package com.undefinedlabs.scope.it;

import com.undefinedlabs.scope.CarLocationApplication;
import com.undefinedlabs.scope.model.CarLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarLocationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarLocationIT {

    private static final String SUCCESS_CAR_UUID = "9E219725-490E-4509-A42D-D0388DF317D4";
    private static final String NOT_FOUND_CAR_UUID = "";
    private static final String ERROR_CAR_UUID = "9E219725-490E-4509-A42D-D0388DF317DG";

    @LocalServerPort
    private int randomServerPort;

    @Test
    public void should_request_by_uuid_and_obtain_car_location() {
        //Given
        final RestTemplate restTemplate = new RestTemplate();

        //When
        final CarLocation carLocation = restTemplate.getForObject("http://localhost:" + randomServerPort + "/car/"+SUCCESS_CAR_UUID+"?q=foobar", CarLocation.class);

        //Then
        assertThat(carLocation).isNotNull();
        assertThat(carLocation.getUuid()).isEqualToIgnoringCase(SUCCESS_CAR_UUID);
        assertThat(carLocation.getLatitude()).isNotNull();
        assertThat(carLocation.getLongitude()).isNotNull();
    }

    @Test
    public void should_request_by_uuid_and_obtain_not_found() {
        //Given
        final RestTemplate restTemplate = new RestTemplate();

        //When
        final CarLocation carLocation = restTemplate.getForObject("http://localhost:" + randomServerPort + "/car/"+NOT_FOUND_CAR_UUID+"?q=foobar", CarLocation.class);

        //Then
        assertThat(carLocation).isNotNull();
        assertThat(carLocation.getUuid()).isEqualToIgnoringCase(SUCCESS_CAR_UUID);
        assertThat(carLocation.getLatitude()).isNotNull();
        assertThat(carLocation.getLongitude()).isNotNull();
    }

    @Test
    public void should_request_by_uuid_and_obtain_error() {
        //Given
        final RestTemplate restTemplate = new RestTemplate();

        //When
        final CarLocation carLocation = restTemplate.getForObject("http://localhost:" + randomServerPort + "/car/"+ERROR_CAR_UUID+"?q=foobar", CarLocation.class);

        //Then
        assertThat(carLocation).isNotNull();
        assertThat(carLocation.getUuid()).isEqualToIgnoringCase(SUCCESS_CAR_UUID);
        assertThat(carLocation.getLatitude()).isNotNull();
        assertThat(carLocation.getLongitude()).isNotNull();
    }

}
