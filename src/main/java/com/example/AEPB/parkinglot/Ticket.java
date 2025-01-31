package com.example.AEPB.parkinglot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private String plateNumber;

    private Integer parkingLotIndex;
}
