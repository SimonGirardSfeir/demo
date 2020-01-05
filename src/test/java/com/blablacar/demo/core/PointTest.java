package com.blablacar.demo.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void move() throws Exception {
        Point p = new Point(1, 2, Direction.NORTH, 5, 5);

        char[] chars = "LFLFLFLFF".toCharArray();

        p.move(chars);

        Point expected = new Point(1, 3, Direction.NORTH, 5, 5);

        assertEquals(expected, p);
    }

    @Test
    void moveWrongInstruction() {
        Point p = new Point(1, 2, Direction.NORTH, 5, 5);

        char[] chars = "coucou".toCharArray();

        Exception exception = assertThrows(Exception.class, () -> {p.move(chars);});
    }


}