package com.nsarvar.decathlon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecathlonTest {

    @Test
    public void test_E_100M(){
        long points = Decathlon.Event.E_100M.calculateResult("12.61");
        assertEquals(points, 537);
    }

    @Test
    public void test_E_LONG_JUMP(){
        long points = Decathlon.Event.E_LONG_JUMP.calculateResult("5.00");
        assertEquals(383, points);
    }

    @Test
    public void test_E_SHOT_PUT(){
        long points = Decathlon.Event.E_SHOT_PUT.calculateResult("9.22");
        assertEquals(439, points);
    }


    @Test
    public void test_E_HIGH_JUMP(){
        long points = Decathlon.Event.E_HIGH_JUMP.calculateResult("1.50");
        assertEquals(389, points);
    }

    @Test
    public void test_E_400M(){
        long points = Decathlon.Event.E_400M.calculateResult("60.39");
        assertEquals(401, points);
    }

    @Test
    public void test_E_100M_HURDLES(){
        long points = Decathlon.Event.E_100M_HURDLES.calculateResult("16.43");
        assertEquals(686, points);
    }

    @Test
    public void test_E_DISCUS_THROW(){
        long points = Decathlon.Event.E_DISCUS_THROW.calculateResult("21.60");
        assertEquals(303, points);
    }

    @Test
    public void test_E_POLE_VAUNT(){
        long points = Decathlon.Event.E_POLE_VAUNT.calculateResult("2.60");
        assertEquals(264, points);
    }

    @Test
    public void test_E_JAVELIN_THROW(){
        long points = Decathlon.Event.E_JAVELIN_THROW.calculateResult("35.81");
        assertEquals(382, points);
    }

    @Test
    public void test_E_1500M(){
        long points = Decathlon.Event.E_1500M.calculateResult("5.25.72");
        assertEquals(421, points);
    }
}