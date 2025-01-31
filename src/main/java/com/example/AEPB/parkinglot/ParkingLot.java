package com.example.AEPB.parkinglot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLot {

    private Integer size;

    private Map<String, Integer> parkingLocation;
    private List<Car> cars;

    public Optional<String> park(Car car) {

        if (parkingLocation.containsKey(car.getPlateNumber())) {
            throw new RuntimeException("plateNumber is exist");
        }

        if (parkingLocation.size() >= size) {
            return Optional.empty();
        }

        for (int location = 0; location < cars.size(); location++) {
            if (Objects.isNull(cars.get(location))) {
                parkingLocation.put(car.getPlateNumber(), location);
                cars.add(location, car);
                break;
            }
        }

        return Optional.of(car.getPlateNumber());
    }

    public Car pickUp(String plateNumber) {
        Integer location = parkingLocation.get(plateNumber);
        if (Objects.isNull(location)) {
            return null;
        }
        Car car = cars.get(location);
        cars.add(location, null);
        parkingLocation.remove(plateNumber);
        return car;
    }

    public Integer getRemainingCapacity() {
        return size - parkingLocation.size();
    }

    public Double getVacancyRate() {
        return getRemainingCapacity() * 1.0 / size;
    }

}
