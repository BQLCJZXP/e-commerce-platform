package com.source.ecommerce.watches.service;

import com.source.ecommerce.watches.dto.OrderResponseDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OrderCostCalculatorImplTest {

    @Test
    void calculateThePriceOfTheExampleListGivenWatches() {
        //given
        List<String> givenWatchesList = List.of("001", "001", "001", "001", "004", "004");
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setPrice(360L);

        OrderCostCalculator orderCostCalculatorMock;

        orderCostCalculatorMock = mock(OrderCostCalculator.class);

        given(orderCostCalculatorMock
                .calculateThePriceOfTheGivenWatches(givenWatchesList)).willReturn(orderResponseDTO);

        //when
        OrderResponseDTO orderResponseDTOResult = orderCostCalculatorMock
                .calculateThePriceOfTheGivenWatches(givenWatchesList);

        Long expectedPrice = orderResponseDTO.getPrice();
        //Long resultPrice = Optional.ofNullable(orderResponseDTOResult.getPrice()).get();
        Long resultPrice = orderResponseDTOResult.getPrice();
        System.out.println(resultPrice);

        //then
        Assert.assertEquals(expectedPrice, resultPrice);
    }

    @Test
    void calculateThePriceOfOneGivenWatch() {
        //given
        List<String> givenWatchesList = List.of("001");
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setPrice(100L);

        OrderCostCalculator orderCostCalculatorMock;

        orderCostCalculatorMock = mock(OrderCostCalculator.class);

        given(orderCostCalculatorMock
                .calculateThePriceOfTheGivenWatches(givenWatchesList)).willReturn(orderResponseDTO);

        //when
        OrderResponseDTO orderResponseDTOResult = orderCostCalculatorMock
                .calculateThePriceOfTheGivenWatches(givenWatchesList);

        Long expectedPrice = orderResponseDTO.getPrice();
        //Long resultPrice = Optional.ofNullable(orderResponseDTOResult.getPrice()).get();
        Long resultPrice = orderResponseDTOResult.getPrice();
        System.out.println(resultPrice);

        //then
        Assert.assertEquals(expectedPrice, resultPrice);
    }

    @Test
    void calculateThePriceOfTheEmptyListGivenWatches() {
        //given
        List<String> givenWatchesList = Collections.emptyList();
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setPrice(0L);

        OrderCostCalculator orderCostCalculatorMock;

        orderCostCalculatorMock = mock(OrderCostCalculator.class);

        given(orderCostCalculatorMock
                .calculateThePriceOfTheGivenWatches(givenWatchesList)).willReturn(orderResponseDTO);

        //when
        OrderResponseDTO orderResponseDTOResult = orderCostCalculatorMock
                .calculateThePriceOfTheGivenWatches(givenWatchesList);

        Long expectedPrice = orderResponseDTO.getPrice();
        Long resultPrice = orderResponseDTOResult.getPrice();
        System.out.println(resultPrice);

        //then
        Assert.assertEquals(expectedPrice, resultPrice);
    }

    @Test
    void calculateTheDiscountPriceOfTheExampleListGivenWatches() {
        //given
        List<String> givenWatchesList = List.of("002", "002");
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setPrice(120L);

        OrderCostCalculator orderCostCalculatorMock;

        orderCostCalculatorMock = mock(OrderCostCalculator.class);

        given(orderCostCalculatorMock
                .calculateThePriceOfTheGivenWatches(givenWatchesList)).willReturn(orderResponseDTO);

        //when
        OrderResponseDTO orderResponseDTOResult = orderCostCalculatorMock
                .calculateThePriceOfTheGivenWatches(givenWatchesList);

        Long expectedPrice = orderResponseDTO.getPrice();
        Long resultPrice = orderResponseDTOResult.getPrice();
        System.out.println(resultPrice);

        //then
        Assert.assertEquals(expectedPrice, resultPrice);
    }
}