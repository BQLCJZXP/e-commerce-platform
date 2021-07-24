package com.source.ecommerce.watches.service;

import com.source.ecommerce.watches.dto.OrderResponseDTO;
import com.source.ecommerce.watches.model.Watch;
import com.source.ecommerce.watches.repository.WatchesRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class OrderCostCalculatorImpl implements OrderCostCalculator {

    private final WatchesRepository watchesRepository;

    public OrderCostCalculatorImpl(final WatchesRepository watchesRepository) {
        this.watchesRepository = watchesRepository;
    }

    @Override
    public OrderResponseDTO calculateThePriceOfTheGivenWatches(final List<String> listOfWatches) {
        Long result = 0L;
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        Set<Watch> watchList = watchesRepository.findByWatchId(listOfWatches);

        if (watchList.isEmpty()) {
            orderResponseDTO.setPrice(0L);
            return orderResponseDTO;
        }
        Map<String, Integer> watchQuantityMap = calculateWatchesQuantity(listOfWatches);

        Set<Map.Entry<String, Integer>> watchQuantitySet = watchQuantityMap.entrySet();

        for (Map.Entry<String, Integer> element : watchQuantitySet) {
            Watch watch = findWatchFromListById(watchList, element.getKey());

            int totalPriceWithoutDiscount = element.getValue() * watch.getUnitPrice();
            int discount = calculateDiscount(element.getValue(), watch.getDiscount(), watch.getDiscountQuantity());
            int summaryWatchTypePrice = totalPriceWithoutDiscount - discount;

            result = result + summaryWatchTypePrice;
            orderResponseDTO.setPrice(result);
        }
        return orderResponseDTO;
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

    private Watch findWatchFromListById(final Set<Watch> watchList, final String watchId) {

        return watchList
                .stream()
                .filter(watch -> watch.getWatchId().equals(watchId))
                .findFirst().orElse(null);
    }
}
