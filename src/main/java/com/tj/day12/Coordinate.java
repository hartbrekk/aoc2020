package com.tj.day12;

import static com.tj.day12.Dir.E;
import static com.tj.util.Utils.log;

public class Coordinate {

    public Coordinate(Dir pointTo, Integer x, Integer y) {
        this.pointTo = pointTo;
        this.x = x;
        this.y = y;
    }

    public Coordinate(Integer x, Integer y, Integer wn, Integer we, Integer ws, Integer ww) {
        this.pointTo = pointTo;
        this.x = x;
        this.y = y;
        this.wn = wn;
        this.we = we;
        this.ws = ws;
        this.ww = ww;
    }

    public Coordinate() {
    }

    public int i = 1;
    public Dir pointTo = E;
    public Integer x = 0;
    public Integer y = 0;
    public Integer wn = 0;
    public Integer we = 0;

    public Integer ws = 0;
    public Integer ww = 0;

    public void add(Coordinate other) {
        this.pointTo = other.pointTo;
        this.x = this.x + other.x;
        this.y = this.y + other.y;
    }

    public void addWithWay(Coordinate other) {
        this.pointTo = other.pointTo;
        this.x = this.x + other.x;
        this.y = this.y + other.y;
        this.wn = other.wn;
        this.we = other.we;
        this.ws = other.ws;
        this.ww = other.ww;
        log(toString());
    }

    @Override
    public String toString() {
        return i++ + ") (x, y)=(" + this.x + ", " + this.y + ") Waypoint (n,e,s,w)=(" + this.wn + ", " + this.we + ", " + this.ws + ", " + this.ww + ")";
    }
}
/**
 * F10 moves the ship to the waypoint 10 times (a total of 100 units east and 10 units north), leaving the ship at east 100, north 10. The waypoint stays 10 units east and 1 unit north of the ship.
 * N3 moves the waypoint 3 units north to 10 units east and 4 units north of the ship. The ship remains at east 100, north 10.
 * F7 moves the ship to the waypoint 7 times (a total of 70 units east and 28 units north), leaving the ship at east 170, north 38. The waypoint stays 10 units east and 4 units north of the ship.
 * R90 rotates the waypoint around the ship clockwise 90 degrees, moving it to 4 units east and 10 units south of the ship. The ship remains at east 170, north 38.
 * F11 moves the ship to the waypoint 11 times (a total of 44 units east and 110 units south), leaving the ship at east 214, south 72. The waypoint stays 4 units east and 10 units south of the ship.
 */