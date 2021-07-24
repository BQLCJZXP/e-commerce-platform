package com.source.ecommerce.watches.service;

import com.source.ecommerce.watches.dto.OrderResponseDTO;

import java.util.List;

public interface OrderCostCalculator {

    OrderResponseDTO calculateThePriceOfTheGivenWatches(final List<String> listOfWatches);

}
