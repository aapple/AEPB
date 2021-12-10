package com.example.AEPB;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;


class SmartParkingLotManagerTest {

    private OrderlyParkingLot getOrderlyParkingLot() {
        Car car1 = Car.builder().plateNumber("123").build();
        Car car2 = Car.builder().plateNumber("456").build();
        Car car3 = Car.builder().plateNumber("789").build();

        List<Car> cars = new ArrayList<>();
        cars.add(0, car1);
        cars.add(1, car2);
        cars.add(2, car3);

        Map<String, Integer> parkingLocation = new HashMap<>();
        parkingLocation.put(car1.getPlateNumber(), 0);
        parkingLocation.put(car2.getPlateNumber(), 1);
        parkingLocation.put(car3.getPlateNumber(), 2);
        OrderlyParkingLot orderlyParkingLot = OrderlyParkingLot.builder().size(3).cars(cars).parkingLocation(
                parkingLocation).build();
        return orderlyParkingLot;
    }

    @Test
    void should_park_max_capacity_parkinglot_when_parking_given_parkinglot_is_not_full() {

        OrderlyParkingLot orderlyParkingLotMin = getOrderlyParkingLot();
        OrderlyParkingLot orderlyParkingLotMid = getOrderlyParkingLot();
        OrderlyParkingLot orderlyParkingLotMax = getOrderlyParkingLot();

        orderlyParkingLotMid.pickUp("123");

        orderlyParkingLotMax.pickUp("123");
        orderlyParkingLotMax.pickUp("456");

        SmartParkingLotManager smartParkingLotManager = new SmartParkingLotManager(
                List.of(orderlyParkingLotMin, orderlyParkingLotMid, orderlyParkingLotMax));
        Car car = Car.builder().plateNumber("abc").build();
        Optional<Ticket> ticket = smartParkingLotManager.park(car);

        assertEquals(2,ticket.get().getParkingLotIndex());
        assertEquals(0,orderlyParkingLotMax.getParkingLocation().get(ticket.get().getPlateNumber()));
        assertEquals(car,orderlyParkingLotMax.getCars().get(orderlyParkingLotMax.getParkingLocation().get(ticket.get().getPlateNumber())));
    }


    @Test
    void should_fail_when_parking_given_parkinglot_is_full() {
        OrderlyParkingLot orderlyParkingLotMin = getOrderlyParkingLot();
        OrderlyParkingLot orderlyParkingLotMid = getOrderlyParkingLot();
        OrderlyParkingLot orderlyParkingLotMax = getOrderlyParkingLot();

        SmartParkingLotManager smartParkingLotManager = new SmartParkingLotManager(
                List.of(orderlyParkingLotMin, orderlyParkingLotMid, orderlyParkingLotMax));
        Car car = Car.builder().plateNumber("abc").build();
        Optional<Ticket> ticket = smartParkingLotManager.park(car);

        assertFalse(ticket.isPresent());

    }


}