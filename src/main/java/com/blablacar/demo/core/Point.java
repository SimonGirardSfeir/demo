package com.blablacar.demo.core;

import java.util.Objects;

public class Point {

    /** Abscisse du point */
    private int X;

    private int maxX;

    /** Ordonnée du point */
    private int Y;

    private int maxY;

    /** Direction du point */
    private Direction d;

    Point(int x, int y, Direction d, int maxX, int maxY) {
        X = x;
        Y = y;
        this.d = d;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    private void goEast() {
        if(X < maxX) {
            this.X = ++X;
        }
    }

    private void goWest() {
        if(X != 0) {
            this.X = --X;
        }
    }

    private void goNorth() {
        if(Y < maxY) {
            this.Y = ++Y;
        }
    }

    private void goSouth() {
        if(Y != 0) {
            this.Y = --Y;
        }
    }

    private Direction getD() {
        return d;
    }

    private void setD(Direction d) {
        this.d = d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return X == point.X &&
                maxX == point.maxX &&
                Y == point.Y &&
                maxY == point.maxY &&
                d == point.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, maxX, Y, maxY, d);
    }

    void move(char[] chars) throws Exception {

        for(char c: chars) {

            if(c == 'L'){
                left();
            } else if (c == 'R') {
                right();
            } else if (c == 'F') {
                if(getD() == Direction.EAST) {
                    goEast();
                } else if (getD() == Direction.NORTH) {
                    goNorth();
                } else if (getD() == Direction.SOUTH) {
                    goSouth();
                } else if (getD() == Direction.WEST) {
                    goWest();
                }
            } else {
                throw new Exception("Instruction de mouvement invalide");
            }
        }
    }

    private void left() {
        if(getD() == Direction.WEST) {
            setD(Direction.SOUTH);
        } else if (getD() == Direction.SOUTH){
            setD(Direction.EAST);
        } else if(getD() == Direction.EAST) {
            setD(Direction.NORTH);
        } else {
            setD(Direction.WEST);
        }
    }

    private void right() {
        if(getD() == Direction.WEST) {
            setD(Direction.NORTH);
        } else if (getD() == Direction.NORTH){
            setD(Direction.EAST);
        } else if(getD() == Direction.EAST) {
            setD(Direction.SOUTH);
        } else {
            setD(Direction.WEST);
        }
    }

    @Override
    public String toString() {
        return "Point : " +
                "Abscisse=" + X +
                ", Ordonnée=" + Y +
                ", Direction=" + d +
                " -";
    }
}
