package com.source.ecommerce.watches.service;

import com.source.ecommerce.watches.dto.ResponseDTO;
import com.source.ecommerce.watches.model.Watch;
import com.source.ecommerce.watches.repository.WatchesRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class WatchesService {

    private final WatchesRepository watchesRepository;

    public WatchesService(WatchesRepository watchesRepository) {
        this.watchesRepository = watchesRepository;
    }

    public Watch getWatchDetailsByWatchId(String watchId) {
        return watchesRepository.findByWatchId(watchId);
    }

    public ResponseDTO calculateThePriceOfTheGivenWatches(final List<String> listOfWatches) {
        Long result = 0L;
        ResponseDTO responseDTO = new ResponseDTO();

        Set<Watch> watchList = watchesRepository.findByWatchId(listOfWatches);

        if (watchList.isEmpty()) {
            responseDTO.setPrice(0L);
            return responseDTO;
        }
        Map<String, Integer> watchQuantityMap = calculateWatchesQuantity(listOfWatches);

        Set<Map.Entry<String, Integer>> watchQuantitySet = watchQuantityMap.entrySet();

        for (Map.Entry<String, Integer> element : watchQuantitySet) {
            Watch watch = findWatchById(watchList, element.getKey());

            int totalPriceWithoutDiscount = element.getValue() * watch.getUnitPrice();
            int discount = calculateDiscount(element.getValue(), watch.getDiscount(), watch.getDiscountQuantity());
            int summaryWatchTypePrice = totalPriceWithoutDiscount - discount;

            result = result + summaryWatchTypePrice;
            responseDTO.setPrice(result);
        }
        return responseDTO;
    }

    private Watch findWatchById(final Set<Watch> watchList, final String watchId) {

        return watchList
                .stream()
                .filter(watch -> watch.getWatchId().equals(watchId))
                .findFirst().orElse(null);
    }

    private static Integer calculateDiscount(final int watchesQuantity, final Integer discount,
                                             final Integer discountQuantity) {

        if (discount == null || discount == 0) {
            return 0;
        }
        if (discountQuantity == null || discountQuantity == 0) {
            return 0;
        }
        int numberOfDiscounts = (watchesQuantity / discountQuantity) % discountQuantity;
        return numberOfDiscounts * discount;
    }

    private Map<String, Integer> calculateWatchesQuantity(final List<String> listOfWatchIds) {
        Map<String, Integer> resultMap = new HashMap<>();

        for (String watchId : listOfWatchIds) {
            if (resultMap.containsKey(watchId)) {
                resultMap.put(watchId, resultMap.get(watchId) + 1);
            } else {
                resultMap.put(watchId, 1);
            }
        }
        return resultMap;
    }
}