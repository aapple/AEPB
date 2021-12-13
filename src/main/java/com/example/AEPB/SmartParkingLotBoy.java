package com.example.AEPB;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class SmartParkingLotBoy extends ParkingBoy {

    private List<ParkingLot> parkingLotList;

    @Override
    public Optional<Ticket> park(Car car){
        Ticket ticket = new Ticket();
        Optional<ParkingLot> parkingLot = parkingLotList.stream().max(
                Comparator.comparingInt(ParkingLot::getRemainingCapacity));

        if (parkingLot.isEmpty()){
            return Optional.empty();
        }
        Optional<String> plateNumber = parkingLot.get().park(car);
        if (plateNumber.isEmpty()){
            return Optional.empty();
        }
        int index = parkingLotList.indexOf(parkingLot.get());
        ticket.setParkingLotIndex(index);
        ticket.setPlateNumber(plateNumber.get());
        return Optional.of(ticket);
    }

    @Override
    public Car pickUp(Ticket ticket){
        return parkingLotList.get(ticket.getParkingLotIndex()).pickUp(ticket.getPlateNumber());
    }
}
