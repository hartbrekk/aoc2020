package com.tj.day13;

import com.tj.util.Utils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static com.tj.util.Utils.*;

public class Day13 {
    public static void main(String[] args) {
        List<String> lines = getData("data.txt");
//        log(lines.size());

        List<String> tc = getData("tc.txt");

        String tcfile = getDataAsOneString("tc.txt");
        parse(tcfile, 1068773L);
        String wholefile = getDataAsOneString("data.txt");

        parse(wholefile, 924556795459228L);

//        Integer testCount = parse(tc);
//
//        log("TestCount:" + testCount);
//
//        Integer res1 = parse(lines);
//
//        log("Result1:" + res1);
//
//        Integer testCount2 = p2(parse2(tc));
//        log("TestCount:" + testCount2);
//
//        Integer res2 = p2(parse2(lines));
    }

    protected static Integer parse(List<String> input) {

        List<BigInteger> list = input.stream().map(line -> new BigInteger(line)).collect(Collectors.toList());


//        output.forEach(System.out::println);

        return 0;
    }


    protected static List<String> parse(String wholeFile, Long stpoint) {
        List<String> map = new ArrayList<>();
        //Preserve a new line for internal parsing***
        String[] records = wholeFile.split("\r\n");
//        Arrays.stream(records).forEach(Utils::log);

        Integer earDepTime = Integer.valueOf(records[0]);

        String[] allBuses = records[1].split(",");
        List<Integer> eligBuses = Arrays.stream(allBuses).filter(b -> !"x".equalsIgnoreCase(b)).map(Integer::valueOf).collect(Collectors.toList());

//        eligBuses.stream().forEach(Utils::log);
//        Arrays.stream(records).forEach(Utils::log);

        int maxBusDir = eligBuses.stream().mapToInt(v -> v).max().getAsInt();
        log("maxBusDir=" + maxBusDir);
        Map<Integer, Integer> cache = new TreeMap<>();
        for (Integer eb : eligBuses) {

            for (int i = earDepTime; i < (earDepTime + eb); i++) {
                if (i % eb == 0) {
                    cache.put(eb, i);
                }
            }

        }
//        log(cache);

        Integer min = earDepTime + maxBusDir;
        Integer minBus = -1;
        for (Map.Entry<Integer, Integer> e : cache.entrySet()) {
            if (e.getValue() <= min) {
                min = e.getValue();
                minBus = e.getKey();
            }
        }
//        log("Min Bus="+minBus);
//        log("Min ="+min);
//
//        log("Ans="+((min-earDepTime)*minBus));

        Long earDep = stpoint;
        Long firstdivisor = -1L;
        for (long l = earDep; l < (earDep + maxBusDir); l++) {
            if (l % maxBusDir == 0) {
                firstdivisor = l;
                log("First divisor is " + firstdivisor);
            }
        }

        int idxMaxBus = -1;
        for (int i = 0; i < allBuses.length; i++) {
            if ((maxBusDir + "").equals(allBuses[i])) {
                idxMaxBus = i;
                break;
            }
        }
        log("idxMaxBus " + idxMaxBus);

        List<Pair<Integer, Integer>> diffs = new ArrayList<>();
        for (int i = 0; i < allBuses.length; i++) {
            if (allBuses[i].equals("x")) {
                continue;
            }
            diffs.add(Pair.of(Integer.valueOf(allBuses[i]), i - idxMaxBus));
        }

//        diffs.stream().forEach(Utils::log);
        long found = -1L;
        for (long l = firstdivisor; found < 0l; l = l + maxBusDir) {
            boolean isFound = false;
            for (Pair<Integer, Integer> p : diffs) {
                Integer bus = p.getLeft();
                Integer bdiff = p.getRight();
                if ((l + bdiff) % bus != 0) {
                    isFound = false;
                    break;
                }
                isFound = true;
            }
            if (isFound) {
                found = l;

            }
        }
        log("My p2 is " + (found+diffs.get(diffs.size()-1).getRight()));
        log("---------------------------------");
//        diffs = new ArrayList<>();
//        for (int i = 0; i < allBuses.length; i++) {
//            if (allBuses[i].equals("x")) {
//                diffs.add(Pair.of(1, i));
//                continue;
//            }
//            diffs.add(Pair.of(Integer.valueOf(allBuses[i]), i));
//        }
//
//        int first =diffs.get(0).getLeft();
//        for (long l = earDep; l < (earDep + first); l++) {
//            if (l % first == 0) {
//                firstdivisor = l;
//                log("First divisor is " + firstdivisor);
//            }
//        }
//
//        Long ans = GFG.findMinX(diffs, earDep);
////        for (long l = firstdivisor; ; l = l + first) {
////            boolean isFound = false;
////            for (Pair<Integer, Integer> p : diffs) {
////                Integer bus = p.getLeft();
////                Integer bdiff = p.getRight();
////                if ((l + bdiff) % bus != 0) {
////                    isFound = false;
////                    break;
////                }
////                isFound = true;
////            }
////            if (isFound) {
////
////                found = l;
////                break;
////
////            }
////        }
//        log("My p3 is " + (ans));
        return map;
    }

    protected static Integer p1(final List<BigInteger> list) {
        Integer sum = 0;
        return sum;
    }

    protected static Integer p2(List<BigInteger> list) {
        Integer sum = 0;
        return sum;
    }
}
