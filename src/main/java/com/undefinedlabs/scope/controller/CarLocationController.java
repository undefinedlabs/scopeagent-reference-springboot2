package com.undefinedlabs.scope.controller;

import com.undefinedlabs.scope.model.dto.CarLocationDTO;
import com.undefinedlabs.scope.service.CarLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CarLocationDTO getByUuid(@PathVariable(name="uuid") String uuid){
        return this.carLocationService.getFromRemote(uuid);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public CarLocationDTO save(@RequestBody CarLocationDTO carLocation){
        return this.carLocationService.save(carLocation);
    }

    @RequestMapping(value = "/db/{uuid}", method = RequestMethod.GET)
    public CarLocationDTO save(@PathVariable(name="uuid") String uuid){
        return this.carLocationService.findByUuid(uuid);
    }

}
