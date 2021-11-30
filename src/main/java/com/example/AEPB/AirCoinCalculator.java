package com.example.AEPB;

import java.util.Objects;

public class AirCoinCalculator {

    private static final int MAX_AMOUNT = 5000000;

    public Boolean compareAmount(AirCoin airCoin1, AirCoin airCoin2){
        checkAmount(airCoin1, airCoin2);
        return Objects.equals(airCoin1.getAmount(), airCoin2.getAmount());
    }

    private void checkAmount(AirCoin airCoin1, AirCoin airCoin2) {
        if (airCoin1.getAmount() > MAX_AMOUNT || airCoin2.getAmount() > MAX_AMOUNT){
            throw new RuntimeException("amount invalid");
        }
    }
}
