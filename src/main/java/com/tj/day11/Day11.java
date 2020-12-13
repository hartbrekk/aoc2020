package com.tj.day11;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static com.tj.util.Utils.getData;
import static com.tj.util.Utils.log;

public class Day11 {
    public static void main(String[] args) {
        List<String> lines = getData("data.txt");
        log(lines.size());

        List<String> tc = getData("tc.txt");
//        Long testCount = p1(parse(tc));
//        log("TestCount:" + testCount);
//
//        Long res1 = p1(parse(lines));
//        log("Result1:" + res1);

        Long testCount2 = p2(parse(tc));
//        log("TestCount2:" + testCount2);
//
        Long res2 = p2(parse(lines));
//        log("Result2:" + res2);
    }

    protected static Status[][] parse(List<String> input) {

        Status[][] s = new Status[input.size()][];
        int row = 0;


        for (String str : input) {
            int col = 0;
            s[row] = new Status[str.length()];
            while (col < str.length()) {
                s[row][col] = eof(str.charAt(col++));
            }

            row++;
        }
        print(s);
        return s;
    }

    /**
     * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
     * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
     * Otherwise, the seat's state does not change.
     */

    protected static Pair<Boolean, Status[][]> sim(final Status[][] s) {
        Status[][] o = new Status[s.length][];

        //Rule 1
        int rs = s.length;
        boolean change = false;
        for (int i = 0; i < s.length; i++) {
            int cs = s[i].length;
            o[i] = new Status[s[i].length];
            for (int j = 0; j < s[i].length; j++) {
                Status stat = s[i][j];
                o[i][j] = stat;
                if (isSeatable(stat) && !((isOcc(s, i - 1, j - 1) || isOcc(s, i - 1, j) || isOcc(s, i - 1, j + 1)
                        || isOcc(s, i, j - 1) || isOcc(s, i, j) || isOcc(s, i, j + 1) ||
                        isOcc(s, i + 1, j - 1) || isOcc(s, i + 1, j) || isOcc(s, i + 1, j + 1)))

                ) {

                    o[i][j] = Status.O;
                    change = true;
                }
                if (isSeatable(stat) && isOcc(s, i, j)) {
                    int occAdj = 0;
                    if (isOcc(s, i - 1, j - 1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i - 1, j)) {
                        occAdj++;
                    }
                    if (isOcc(s, i - 1, j + 1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i, j - 1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i, j + 1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i + 1, j - 1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i + 1, j)) {
                        occAdj++;
                    }
                    if (isOcc(s, i + 1, j + 1)) {
                        occAdj++;
                    }
                    if (occAdj >= 4) {
                        o[i][j] = Status.E;
                        change = true;
                    }
                }
            }
        }
        int occ = print(o);
        log("Total occupied seats=" + occ);
        return Pair.of(change, o);
    }

    protected static Long p1(final Status[][] s) {
        int i = 0;
        Status[][] o = null;
        log(i + ":");
        Pair<Boolean, Status[][]> p = sim(s);
        while (p.getLeft()) {
            log(i++ + ":");
            p = sim(p.getRight());
        }
        return 0L;
    }

    public static boolean isSeatable(Status st) {
        if (st != Status.F) {
            return true;
        }
        return false;
    }

    public static boolean isOcc(final Status[][] s, int i, int j) {
        if (i >= 0 && j >= 0 && i < s.length && j < s[i].length) {
            if (s[i][j] == Status.O) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOcc(final Status[][] s, int i, int j, int ifactor, int jfactor) {
        while (i >= 0 && j >= 0 && i < s.length && j < s[i].length) {

            if (isSeatable(s[i][j]) && s[i][j] == Status.O) {
                return true;
            }
            if (isSeatable(s[i][j]) && s[i][j] == Status.E) {
                return false;
            }

            i += ifactor;
            j += jfactor;
        }
        return false;
    }

    protected static Pair<Boolean, Status[][]> sim2(final Status[][] s) {
        Status[][] o = new Status[s.length][];

        //Rule 1
        int rs = s.length;
        boolean change = false;
        for (int i = 0; i < s.length; i++) {
            int cs = s[i].length;
            o[i] = new Status[s[i].length];
            for (int j = 0; j < s[i].length; j++) {
                Status stat = s[i][j];
                o[i][j] = stat;
                if (isSeatable(stat) && !((isOcc(s, i - 1, j - 1, -1, -1) || isOcc(s, i - 1, j, -1, 0) || isOcc(s, i - 1, j + 1, -1, 1)
                        || isOcc(s, i, j - 1, 0, -1) || isOcc(s, i, j) || isOcc(s, i, j + 1, 0, 1) ||
                        isOcc(s, i + 1, j - 1, 1, -1) || isOcc(s, i + 1, j, 1, 0) || isOcc(s, i + 1, j + 1, 1, 1)))

                ) {

                    o[i][j] = Status.O;
                    change = true;
                }
                if (isSeatable(stat) && isOcc(s, i, j)) {
                    int occAdj = 0;
                    if (isOcc(s, i - 1, j - 1, -1, -1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i - 1, j, -1, 0)) {
                        occAdj++;
                    }
                    if (isOcc(s, i - 1, j + 1, -1, 1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i, j - 1, 0, -1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i, j + 1, 0, 1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i + 1, j - 1, 1, -1)) {
                        occAdj++;
                    }
                    if (isOcc(s, i + 1, j, 1, 0)) {
                        occAdj++;
                    }
                    if (isOcc(s, i + 1, j + 1, 1, 1)) {
                        occAdj++;
                    }
                    if (occAdj >= 5) {
                        o[i][j] = Status.E;
                        change = true;
                    }
                }
            }
        }
        int occ = print(o);
        log("Total occupied seats=" + occ);
        return Pair.of(change, o);
    }

    protected static Long p2(Status[][] s) {
        int i = 0;
        Status[][] o = null;
        log(i + ":");
        Pair<Boolean, Status[][]> p = sim2(s);
        while (p.getLeft()) {
            log(i++ + ":");
            p = sim2(p.getRight());
        }
        return 0L;
    }

    public static Status eof(Character c) {
        for (Status stat : Status.values()) {
            if (stat.label == c) {
                return stat;
            }
        }
        return null;
    }

    protected static Integer print(final Status[][] s) {
        int occ = 0;
        for (int i = 0; i < s.length; i++) {
            System.out.print("|");
            for (int j = 0; j < s[i].length; j++) {

                System.out.print(s[i][j].label);
                if (isOcc(s, i, j)) {
                    occ++;
                }
            }
            System.out.println("|");
        }
        return occ;

    }

}
