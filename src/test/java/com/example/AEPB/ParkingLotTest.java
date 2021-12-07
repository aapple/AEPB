package com.example.AEPB;

import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.ThrowsException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotTest {

    @Test
    void should_success_when_parking_given_parkinglot_is_not_full(){
        ParkingLot parkingLot = ParkingLot.builder().size(100).cars(new HashMap<>(100)).build();
        Car car = Car.builder().plateNumber("123").build();

        Optional<String> plateNumber = parkingLot.park(car);
        assertTrue(plateNumber.isPresent());
    }

    @Test
    void should_success_when_pickUp_given_platenumber_is_exist(){
        ParkingLot parkingLot = ParkingLot.builder().size(100).cars(new HashMap<>(100)).build();
        Car car = Car.builder().plateNumber("123").build();

        Car car1 = parkingLot.pickUp("123");
        assertEquals(car, car1);
    }

    @Test
    void should_fail_when_parking_given_parkinglot_is_full(){
        Car car1 = Car.builder().plateNumber("123").build();
        Car car2 = Car.builder().plateNumber("abc").build();
        ParkingLot parkingLot = ParkingLot.builder().size(1).cars(Map.of(car1.getPlateNumber(), car1)).build();

        Optional<String> plateNumber = parkingLot.park(car2);
        assertFalse(plateNumber.isPresent());
    }

    @Test
    void should_fail_when_pickUp_given_plateNUmber_is_not_exist(){
        Car car1 = Car.builder().plateNumber("123").build();
        ParkingLot parkingLot = ParkingLot.builder().size(1).cars(Map.of(car1.getPlateNumber(), car1)).build();

        Car car = parkingLot.pickUp("abc");
        assertNull(car);
    }

    @Test
    void should_fail_when_parking_given_plateNumber_is_exist(){
        Car car1 = Car.builder().plateNumber("123").build();
        ParkingLot parkingLot = ParkingLot.builder().size(1).cars(Map.of(car1.getPlateNumber(), car1)).build();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.park(car1);
        });

        assertEquals("plateNumber is exist", exception.getMessage());
    }

}
