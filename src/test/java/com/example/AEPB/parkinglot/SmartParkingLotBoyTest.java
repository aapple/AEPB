package com.example.AEPB.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class SmartParkingLotBoyTest {

    private ParkingLot buildParkingLot() {
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
        ParkingLot parkingLot = ParkingLot.builder().size(3).cars(cars).parkingLocation(
                parkingLocation).build();
        return parkingLot;
    }

    @Test
    void should_park_max_capacity_parkinglot_when_parking_given_parkinglot_is_not_full() {

        ParkingLot parkingLotMin = buildParkingLot();
        ParkingLot parkingLotMid = buildParkingLot();
        ParkingLot parkingLotMax = buildParkingLot();

        parkingLotMid.pickUp("123");

        parkingLotMax.pickUp("123");
        parkingLotMax.pickUp("456");

        SmartParkingLotBoy smartParkingLotBoy = new SmartParkingLotBoy(
                List.of(parkingLotMin, parkingLotMid, parkingLotMax));
        Car car = Car.builder().plateNumber("abc").build();
        Optional<Ticket> ticket = smartParkingLotBoy.park(car);

        assertEquals(2,ticket.get().getParkingLotIndex());
        assertEquals(0, parkingLotMax.getParkingLocation().get(ticket.get().getPlateNumber()));
        assertEquals(car, parkingLotMax.getCars().get(parkingLotMax.getParkingLocation().get(ticket.get().getPlateNumber())));
    }


    @Test
    void should_fail_when_parking_given_parkinglot_is_full() {
        ParkingLot parkingLotMin = buildParkingLot();
        ParkingLot parkingLotMid = buildParkingLot();
        ParkingLot parkingLotMax = buildParkingLot();

        SmartParkingLotBoy smartParkingLotBoy = new SmartParkingLotBoy(
                List.of(parkingLotMin, parkingLotMid, parkingLotMax));
        Car car = Car.builder().plateNumber("abc").build();
        Optional<Ticket> ticket = smartParkingLotBoy.park(car);

        assertFalse(ticket.isPresent());

    }


}