package com.source.ecommerce.watches.service;

import com.source.ecommerce.watches.dto.ResponseDTO;

import java.util.List;

public interface OrderCostCalculator {

    ResponseDTO calculateThePriceOfTheGivenWatches(final List<String> listOfWatches);

}
