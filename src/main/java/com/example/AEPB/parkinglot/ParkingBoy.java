package com.example.AEPB.parkinglot;

import java.util.Optional;

public abstract class ParkingBoy {

    abstract Optional<Ticket> park(Car car);

    abstract Car pickUp(Ticket ticket);
}
