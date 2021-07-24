package com.source.ecommerce.watches.service;

import com.source.ecommerce.watches.dto.ResponseDTO;
import com.source.ecommerce.watches.model.Watch;
import com.source.ecommerce.watches.repository.WatchesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchesService {

    private final WatchesRepository watchesRepository;
    private final OrderCostCalculator orderCostCalculator;

    public WatchesService(final WatchesRepository watchesRepository,
                          final OrderCostCalculator orderCostCalculator) {
        this.watchesRepository = watchesRepository;
        this.orderCostCalculator = orderCostCalculator;
    }

    public Watch getWatchDetailsByWatchId(String watchId) {
        return watchesRepository.findByWatchId(watchId);
    }

    public ResponseDTO calculateThePriceOfTheGivenWatches(final List<String> listOfWatches) {
        return orderCostCalculator.calculateThePriceOfTheGivenWatches(listOfWatches);
    }
}