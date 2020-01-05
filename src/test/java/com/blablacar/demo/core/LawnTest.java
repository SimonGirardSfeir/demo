package com.blablacar.demo.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LawnTest {


    @Test
    void addPoint() throws Exception {
        String initialPosition = "1 2 N";
        String instructions = "LFLFLFLFF";

        List<String> list = new ArrayList<>();
        list.add(initialPosition);
        list.add(instructions);

        Lawn lawn = new Lawn(5, 5);

        lawn.addPoint(list);

        assertEquals(1, lawn.getPoints().size());

        Point p = new Point(1, 3, Direction.NORTH, 5, 5);

        assertEquals(p, lawn.getPoints().get(0));
    }

    @Test
    void addPointWithWrongDimension() {

        String initialPosition = "55 2 N";
        String instructions = "LFLFLFLFF";

        List<String> list = new ArrayList<>();
        list.add(initialPosition);
        list.add(instructions);

        Lawn lawn = new Lawn(5, 5);

        //L'abscise du point étant hors du cadre de la pelouse, une exception devrait être lancée
        Exception exception = assertThrows(Exception.class, () -> {lawn.addPoint(list);});

    }

    @Test
    void addPointWithWrongDirection() {

        String initialPosition = "1 2 Z";
        String instructions = "LFLFLFLFF";

        List<String> list = new ArrayList<>();
        list.add(initialPosition);
        list.add(instructions);

        Lawn lawn = new Lawn(5, 5);

        //La direction étant fausse une exception devrait être lancée
        Exception exception = assertThrows(Exception.class, () -> {lawn.addPoint(list);});

    }

    @Test
    void addPointWithWrongInstructions() {

        String initialPosition = "1 2 Z";
        String instructions = "mauvaise instruction";

        List<String> list = new ArrayList<>();
        list.add(initialPosition);
        list.add(instructions);

        Lawn lawn = new Lawn(5, 5);

        //L'instruction étant fausse une exception devrait être lancée
        Exception exception = assertThrows(Exception.class, () -> {lawn.addPoint(list);});

    }
}