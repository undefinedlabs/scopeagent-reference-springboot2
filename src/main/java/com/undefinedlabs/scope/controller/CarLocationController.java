package com.undefinedlabs.scope.controller;

import com.undefinedlabs.scope.model.CarLocation;
import com.undefinedlabs.scope.service.CarLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarLocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarLocationController.class);

    private final CarLocationService carLocationService;

    @Autowired
    public CarLocationController(final CarLocationService carLocationService){
        this.carLocationService = carLocationService;
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public CarLocation getByUuid(@PathVariable(name="uuid") String uuid){
        return this.carLocationService.findById(uuid);
    }

}
