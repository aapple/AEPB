package com.example.AEPB;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLot {

    private Integer size;

    private Map<String, Car> cars;

    public Optional<String> park(Car car){
        if (cars.containsKey(car.getPlateNumber())){
            throw new RuntimeException("plateNumber is exist");
        }
        if (cars.size() >= size){
            return Optional.empty();
        }
        cars.put(car.getPlateNumber(), car);
        return Optional.of(car.getPlateNumber());
    }

    public Car pickUp(String plateNumber){
        return cars.get(plateNumber);
    }
}
