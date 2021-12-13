package com.example.AEPB.aircoin;

import com.example.AEPB.aircoin.AirCoin;
import com.example.AEPB.aircoin.AirCoinCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AirCoinCalculatorTest {

    private AirCoinCalculator airCoinCalculator = new AirCoinCalculator();

    @Test
    void should_return_true_when_give_two_equal_amount(){
        // given
        AirCoin airCoin1 = new AirCoin(1.2D);
        AirCoin airCoin2 = new AirCoin(1.2D);

        // when
        Boolean result = airCoinCalculator.compareAmount(airCoin1, airCoin2);

        // then
        assertTrue(result);
    }

    @Test
    void should_return_false_when_give_two_not_equal_amount(){
        // given
        AirCoin airCoin1 = new AirCoin(1.1D);
        AirCoin airCoin2 = new AirCoin(2.1D);

        // when
        Boolean result = airCoinCalculator.compareAmount(airCoin1, airCoin2);

        // then
        assertFalse(result);
    }

    @Test
    void should_throw_exception_when_give_exception_amount(){
        // given
        AirCoin airCoin1 = new AirCoin(500000000000000000D);
        AirCoin airCoin2 = new AirCoin(2.1D);

        // when
        Throwable exception = assertThrows(RuntimeException.class,()->{
            airCoinCalculator.compareAmount(airCoin1, airCoin2);
        });

        // then
        assertEquals("amount invalid", exception.getMessage());
    }
}