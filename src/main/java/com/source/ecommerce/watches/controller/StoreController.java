package com.source.ecommerce.watches.controller;

import com.source.ecommerce.watches.model.Watch;
import com.source.ecommerce.watches.service.WatchesService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/checkout")
public class StoreController {

    private final WatchesService watchesService;

    public StoreController(WatchesService watchesService) {
        this.watchesService = watchesService;
    }

    @GetMapping("/{watchId}")
    public Watch getWatchDetailsByWatchId(@PathVariable String watchId) {
        return watchesService.getWatchDetailsByWatchId(watchId);
    }

    @PostMapping(consumes = "application/json")
    public Long calculatePrice(@RequestBody ArrayList<String> listOfWatches) {
        return watchesService.calculatePrice(listOfWatches);

    }


}
