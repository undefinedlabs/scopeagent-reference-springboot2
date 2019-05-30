package com.undefinedlabs.scope.configuration;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class CarLocationApplicationConfigurationTest {

    @Test
    public void should_return_rest_template_instance(){
        //Given
        final CarLocationApplicationConfiguration sut = new CarLocationApplicationConfiguration();

        //When
        final RestTemplate restTemplate = sut.restTemplate();

        //Then
        assertThat(restTemplate).isNotNull();
    }
}
