package com.source.ecommerce.watches.controller;

import com.source.ecommerce.watches.dto.OrderResponseDTO;
import com.source.ecommerce.watches.model.Watch;
import com.source.ecommerce.watches.service.WatchesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class StoreController {

    private final WatchesService watchesService;

    public StoreController(WatchesService watchesService) {
        this.watchesService = watchesService;
    }

    @PostMapping(consumes = "application/json")
    public OrderResponseDTO calculatePrice(@RequestBody List<String> listOfWatches) {
        return watchesService.calculateThePriceOfTheGivenWatches(listOfWatches);
    }

    @GetMapping("/{watchId}")
    public Watch getWatchDetailsByWatchId(@PathVariable String watchId) {
        return watchesService.getWatchDetailsByWatchId(watchId);
    }
}
