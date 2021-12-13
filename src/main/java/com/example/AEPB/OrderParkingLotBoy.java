package com.example.AEPB;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderParkingLotBoy extends ParkingBoy{

    private List<ParkingLot> parkingLotList;

    @Override
    public Optional<Ticket> park(Car car){
        Ticket ticket = new Ticket();
        for (int i = 0; i < parkingLotList.size(); i++) {
            Optional<String> park = parkingLotList.get(i).park(car);
            if (park.isPresent()){
                ticket.setParkingLotIndex(i);
                ticket.setPlateNumber(park.get());
                break;
            }
        }
        return Optional.of(ticket);
    }

    @Override
    public Car pickUp(Ticket ticket){
        return parkingLotList.get(ticket.getParkingLotIndex()).pickUp(ticket.getPlateNumber());
    }
}
