package com.example.AEPB.parkinglot;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Car {

    private String plateNumber;
}
