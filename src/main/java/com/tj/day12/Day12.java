package com.tj.day12;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tj.day12.Dir.*;
import static com.tj.util.Utils.getData;
import static com.tj.util.Utils.log;
import static java.lang.Math.abs;

public class Day12 {
    public static void main(String[] args) {
        List<String> tc = getData("tc.txt");
        List<String> lines = getData("data.txt");

        int testCount = p1(parse(tc));
        log("TestCount:" + testCount);
        int res1 = p1(parse(lines));
        log("Result1:" + res1);

        int tr2 = p2(parse(tc));
        log("TestCount2:" + tr2);
        int res2 = p2(parse(lines));
        log("Result2:" + res2);

    }

    protected static List<Pair<Dir, Integer>> parse(List<String> input) {
        List<Pair<Dir, Integer>> list = input.stream().map(s -> Pair.of(toDir(s.charAt(0)), Integer.valueOf(s.substring(1)))).collect(Collectors.toList());
//        list.forEach(System.out::println);
        return list;
    }


    protected static List<String> parseAsOneString(String wholeFile) {
        List<String> map = new ArrayList<>();
        //Preserve a new line for internal parsing***
        String[] records = wholeFile.split("\r\n\r\n");
//        Arrays.stream(records).forEach(Utils::log);
        int i = 0;
        for (String rec : records) {
            rec = rec.replaceAll("\r\n", "");
            log(i++ + ":" + rec);
        }
        return map;
    }

    protected static int p1(final List<Pair<Dir, Integer>> list) {
        Coordinate coor = new Coordinate();
        Dir pointTo = E;
        for (Pair<Dir, Integer> p : list) {
            coor.add(nav(coor.pointTo, p));
        }
        log(coor);
        return Math.abs(coor.x) + Math.abs(coor.y);
    }

    public static Coordinate nav(Dir pointingTo, Pair<Dir, Integer> p) {

        switch (p.getLeft()) {
            case E:
                return new Coordinate(pointingTo, p.getRight(), 0);

            case W:
                return new Coordinate(pointingTo, -(p.getRight()), 0);
            case N:
                return new Coordinate(pointingTo, 0, (p.getRight()));

            case S:
                return new Coordinate(pointingTo, 0, -(p.getRight()));

            case F:
                return getForward(pointingTo, p);
            case L:
                return getLeft(pointingTo, p);
            case R:
                return getRight(pointingTo, p);
            default:

        }
        return null;
    }

    protected static Coordinate getForward(Dir pointingTo, Pair<Dir, Integer> p) {
        switch (pointingTo) {
            case E:
                return new Coordinate(pointingTo, p.getRight(), 0);
            case W:
                return new Coordinate(pointingTo, -(p.getRight()), 0);
            case N:
                return new Coordinate(pointingTo, 0, (p.getRight()));
            case S:
                return new Coordinate(pointingTo, 0, -(p.getRight()));

        }
        return null;
    }

    protected static Coordinate getLeft(Dir pointingTo, Pair<Dir, Integer> p) {
        switch (pointingTo) {
            case E:
                switch (p.getRight()) {
                    case 90:
                        return new Coordinate(N, 0, 0);
                    case 180:
                        return new Coordinate(W, 0, 0);
                    case 270:
                        return new Coordinate(S, 0, 0);
                    case 360:
                        return new Coordinate(E, 0, 0);
                }
                break;
            case W:
                switch (p.getRight()) {
                    case 90:
                        return new Coordinate(S, 0, 0);
                    case 180:
                        return new Coordinate(E, 0, 0);
                    case 270:
                        return new Coordinate(N, 0, 0);
                    case 360:
                        return new Coordinate(W, 0, 0);
                }
                break;

            case N:
                switch (p.getRight()) {
                    case 90:
                        return new Coordinate(W, 0, 0);
                    case 180:
                        return new Coordinate(S, 0, 0);
                    case 270:
                        return new Coordinate(E, 0, 0);
                    case 360:
                        return new Coordinate(N, 0, 0);
                }
                break;

            case S:
                switch (p.getRight()) {
                    case 90:
                        return new Coordinate(E, 0, 0);
                    case 180:
                        return new Coordinate(N, 0, 0);
                    case 270:
                        return new Coordinate(W, 0, 0);
                    case 360:
                        return new Coordinate(S, 0, 0);
                }
                break;
        }
        return null;
    }

    protected static Coordinate getRight(Dir pointingTo, Pair<Dir, Integer> p) {
        switch (pointingTo) {
            case E:
                switch (p.getRight()) {
                    case 90:
                        return new Coordinate(S, 0, 0);
                    case 180:
                        return new Coordinate(W, 0, 0);
                    case 270:
                        return new Coordinate(N, 0, 0);
                    case 360:
                        return new Coordinate(E, 0, 0);
                }
                break;
            case W:
                switch (p.getRight()) {
                    case 90:
                        return new Coordinate(N, 0, 0);
                    case 180:
                        return new Coordinate(E, 0, 0);
                    case 270:
                        return new Coordinate(S, 0, 0);
                    case 360:
                        return new Coordinate(W, 0, 0);
                }
                break;

            case N:
                switch (p.getRight()) {
                    case 90:
                        return new Coordinate(E, 0, 0);
                    case 180:
                        return new Coordinate(S, 0, 0);
                    case 270:
                        return new Coordinate(W, 0, 0);
                    case 360:
                        return new Coordinate(N, 0, 0);
                }
                break;

            case S:
                switch (p.getRight()) {
                    case 90:
                        return new Coordinate(W, 0, 0);
                    case 180:
                        return new Coordinate(N, 0, 0);
                    case 270:
                        return new Coordinate(E, 0, 0);
                    case 360:
                        return new Coordinate(S, 0, 0);
                }
                break;
        }
        return null;
    }


    protected static int p2(List<Pair<Dir, Integer>> list) {
        Coordinate coor = new Coordinate(0, 0, 1, 10, 0, 0);

        for (Pair<Dir, Integer> p : list) {
            coor.addWithWay(nav2(coor, p));
        }
        log(coor);
        return Math.abs(coor.x) + Math.abs(coor.y);
    }

    /**
     * Action N means to move the waypoint north by the given value.
     * Action S means to move the waypoint south by the given value.
     * Action E means to move the waypoint east by the given value.
     * Action W means to move the waypoint west by the given value.
     * Action L means to rotate the waypoint around the ship left (counter-clockwise) the given number of degrees.
     * Action R means to rotate the waypoint around the ship right (clockwise) the given number of degrees.
     * Action F means to move forward to the waypoint a number of times equal to the given value.
     */
    public static Coordinate nav2(final Coordinate c, final Pair<Dir, Integer> p) {

        switch (p.getLeft()) {
            case E:

                if (c.we + c.ww + p.getRight() > 0) {
                    return new Coordinate(0, 0, c.wn, c.we + c.ww + p.getRight(), c.ws, 0);
                } else {
                    return new Coordinate(0, 0, c.wn, 0, c.ws, c.we + c.ww + p.getRight());
                }
            case W:
                if (c.we + c.ww - p.getRight() > 0) {
                    return new Coordinate(0, 0, c.wn, c.we + c.ww - p.getRight(), c.ws, 0);
                } else {
                    return new Coordinate(0, 0, c.wn, 0, c.ws, (c.we + c.ww) - (p.getRight()));
                }


            case N:
                if (c.wn + c.ws + p.getRight() > 0) {
                    return new Coordinate(0, 0, c.wn + c.ws + p.getRight(), c.we, 0, c.ww);
                } else {
                    return new Coordinate(0, 0, 0, c.we, c.wn + c.ws + p.getRight(), c.ww);
                }


            case S:
                if (c.wn + c.ws - p.getRight() > 0) {
                    return new Coordinate(0, 0, c.wn + c.ws - p.getRight(), c.we, 0, c.ww);
                } else {
                    return new Coordinate(0, 0, 0, c.we, c.wn + c.ws - p.getRight(), c.ww);
                }
            case F:
                return getForward2(c, p);
            case L:
                return getLeft3(c, p);
            case R:
                return getRight3(c, p);
            default:

        }
        return null;
    }

    protected static Coordinate getForward2(final Coordinate c, Pair<Dir, Integer> p) {
        return new Coordinate((p.getRight() * c.wn) + (p.getRight() * c.ws), (p.getRight() * c.we) + (p.getRight() * c.ww), c.wn, c.we, c.ws, c.ww);
    }

    protected static Coordinate getLeft3(final Coordinate c, Pair<Dir, Integer> p) {
        switch (p.getRight()) {
            case 90:
                return new Coordinate(0, 0, abs(c.we), abs(c.ws), -abs(c.ww), -abs(c.wn));
            case 180:
                return new Coordinate(0, 0, abs(c.ws), abs(c.ww), -abs(c.wn), -abs(c.we));
            case 270:
                return new Coordinate(0, 0, abs(c.ww), abs(c.wn), -abs(c.we), -abs(c.ws));
            case 360:
                return new Coordinate(0, 0, abs(c.wn), abs(c.we), -abs(c.ws), -abs(c.ww));
        }
        return null;
    }

    protected static Coordinate getRight3(final Coordinate c, Pair<Dir, Integer> p) {
        switch (p.getRight()) {
            case 90:
                return new Coordinate(0, 0, abs(c.ww), abs(c.wn), -abs(c.we), -abs(c.ws));
            case 180:
                return new Coordinate(0, 0, abs(c.ws), abs(c.ww), -abs(c.wn), -abs(c.we));
            case 270:
                return new Coordinate(0, 0, abs(c.we), abs(c.ws), -abs(c.ww), -abs(c.wn));
            case 360:
                return new Coordinate(0, 0, abs(c.wn), abs(c.we), -abs(c.ws), -abs(c.ww));
        }
        return null;
    }

    public static Dir toDir(Character c) {
        for (Dir d : Dir.values()) {
            if (d.label == c) {
                return d;
            }
        }
        return null;
    }
}

