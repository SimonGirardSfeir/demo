package com.blablacar.demo.core;

import java.util.ArrayList;
import java.util.List;

public class Lawn {

    /** Dimensions */
    private int width;
    private int height;

    /** Points sur la pelouse */
    private List<Point> points;

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
        this.points = new ArrayList<>();
    }

    public void addPoint(List<String> twoLines) throws Exception {

        String initialPosition = twoLines.get(0);
        String[] data = initialPosition.split(" ");

        int X = Integer.parseInt(data[0]);
        int Y = Integer.parseInt(data[1]);

        /*
         * Ici on s'assure que la première ligne renseigne bien l'abscisse l'ordonnée
         * et la Direction initiale du point.
         * Bien sur, les coordonnées du point doivent être cohérentes avec les dimensions
         * de la pelouse.
         * Et le troisième élément doit bien indiquer une direction
         */
        if(data.length != 3 || X >= width || Y >= height || (!isOk(data[2]))) {
            throw new Exception("Valeur invalide pour le point");
        }

        Point p = new Point(X, Y, directionFromString(data[2]), width, height);

        /*
         * De la deuxième ligne on génère un tableau de caractères
         * Et avec les données de cette ligne, on fait bouger le point.
         */
        char[] charArray = twoLines.get(1).toCharArray();

        p.move(charArray);

        points.add(p);

    }

    /**
     * On s'assure que la donnée d'entrée est bien une direction
     */
    private static boolean isOk(String input) {
        return input.equals("N") || input.equals("S") || input.equals("E") || input.equals("W");

    }

    private static Direction directionFromString(String s) {
        if(s.charAt(0) == 'N') {
            return Direction.NORTH;
        } else if (s.charAt(0) == 'S') {
            return Direction.SOUTH;
        } else if (s.charAt(0) == 'E') {
            return Direction.EAST;
        } else if (s.charAt(0) == 'W') {
            return Direction.WEST;
        } else {
            return null;
        }
    }

    public List<Point> getPoints() {
        return points;
    }
}
