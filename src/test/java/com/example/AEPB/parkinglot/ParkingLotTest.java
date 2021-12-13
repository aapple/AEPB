package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    void should_success_when_parking_given_parkinglot_is_not_full() {
        Car car1 = Car.builder().plateNumber("123").build();
        Car car3 = Car.builder().plateNumber("789").build();

        List<Car> cars = new ArrayList<>();
        cars.add(0, null);
        cars.add(1, null);
        cars.add(2, car3);

        Map<String, Integer> parkingLocation = new HashMap<>();
        parkingLocation.put(car3.getPlateNumber(), 2);
        ParkingLot parkingLot = ParkingLot.builder().size(3).cars(cars).parkingLocation(
                parkingLocation).build();

        Optional<String> plateNumber = parkingLot.park(car1);
        assertEquals(0, parkingLot.getParkingLocation().get(plateNumber.get()));
        assertEquals(car1, parkingLot.getCars().get(0));
    }

    @Test
    void should_park_min_location_when_parking_given_parkinglot_is_not_full() {
        Car car1 = Car.builder().plateNumber("123").build();
        Car car2 = Car.builder().plateNumber("456").build();
        Car car3 = Car.builder().plateNumber("789").build();

        List<Car> cars = new ArrayList<>();
        cars.add(0, car1);
        cars.add(1, null);
        cars.add(2, car3);

        Map<String, Integer> parkingLocation = new HashMap<>();
        parkingLocation.put(car1.getPlateNumber(), 0);
        parkingLocation.put(car3.getPlateNumber(), 2);
        ParkingLot parkingLot = ParkingLot.builder().size(3).cars(cars).parkingLocation(
                parkingLocation).build();

        Optional<String> plateNumber = parkingLot.park(car2);
        assertEquals(1, parkingLot.getParkingLocation().get(plateNumber.get()));
        assertEquals(car2, parkingLot.getCars().get(1));
    }

    @Test
    void should_success_when_pickUp_given_platenumber_is_exist() {
        Car car = Car.builder().plateNumber("123").build();
        Map<String, Integer> parkingLocation = new HashMap<>();
        parkingLocation.put(car.getPlateNumber(), 0);
        List<Car> cars = new ArrayList<>();
        cars.add(0, car);
        ParkingLot parkingLot = ParkingLot.builder().size(1).cars(cars).parkingLocation(
                parkingLocation).build();

        Car car1 = parkingLot.pickUp("123");
        assertEquals(car, car1);
    }

    @Test
    void should_fail_when_parking_given_parkinglot_is_full() {
        Car car1 = Car.builder().plateNumber("123").build();
        Car car2 = Car.builder().plateNumber("abc").build();
        ParkingLot parkingLot = ParkingLot.builder().size(1).cars(List.of(car1)).parkingLocation(
                Map.of(car1.getPlateNumber(), 0)).build();

        Optional<String> plateNumber = parkingLot.park(car2);
        assertFalse(plateNumber.isPresent());
    }

    @Test
    void should_fail_when_pickUp_given_plateNUmber_is_not_exist() {
        Car car1 = Car.builder().plateNumber("123").build();
        ParkingLot parkingLot = ParkingLot.builder().size(1).cars(List.of(car1)).parkingLocation(
                Map.of(car1.getPlateNumber(), 0)).build();

        Car car = parkingLot.pickUp("abc");
        assertNull(car);
    }

    @Test
    void should_fail_when_parking_given_plateNumber_is_exist() {
        Car car1 = Car.builder().plateNumber("123").build();
        ParkingLot parkingLot = ParkingLot.builder().size(1).cars(List.of(car1)).parkingLocation(
                Map.of(car1.getPlateNumber(), 0)).build();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.park(car1);
        });

        assertEquals("plateNumber is exist", exception.getMessage());
    }

}
