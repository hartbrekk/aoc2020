package com.tj.Five;

import com.tj.util.Tuple;
import com.tj.util.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class Five {

    public static void main(String[] args) {
        List<String> lines = Utils.getData("data.txt");
        System.out.println(lines.size());
        List<String> tc = Utils.getData("tc.txt");

        Integer testCount1 = p1(p1a(parse(tc)));
        System.out.println("TestCount1:" + testCount1);

        Integer result1 = p1(p1a(parse(lines)));
        System.out.println("Result1:" + result1)

        ;

        List<Integer> seats = p1a(parse(lines));
        Integer min = p2(seats);

        System.out.println("Min" + min);

        int i = min;

        for (Integer seat : seats) {
            if (seat != i) {
                break;
            }
//            System.out.println("Seat="+seat+" i="+(i++));

        }
        System.out.println("Result2:" + i);
    }

    protected static List<Tuple> parse(List<String> list) {
        return list.stream().map(s -> {
            String row = s.substring(0, 7).replaceAll("F", "0").replaceAll("B", "1");

            String col = s.substring(7).replaceAll("L", "0").replaceAll("R", "1");

            Tuple t = new Tuple(row, col);
            return t;
        }).collect(Collectors.toList());

    }

    protected static List<Integer> p1a(List<Tuple> list) {
        return list.stream().map(t -> {
                    Integer row = Integer.parseInt(t.first, 2);
                    Integer col = Integer.parseInt(t.second, 2);
                    //System.out.println("row:"+row+" col:"+col);
                    Integer seat = (row * 8) + col;
                    return seat;
                }
        ).sorted().collect(Collectors.toList());

    }


    protected static Integer p1(List<Integer> list) {
        return list.stream().max(Integer::compare).get();

    }

    protected static Integer p2(List<Integer> list) {
        return list.stream().min(Integer::compare).get();
    }
}
