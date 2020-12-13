package com.tj.ten;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

import static com.tj.util.Utils.getData;
import static com.tj.util.Utils.log;

public class Ten {
    public static void main(String[] args) {
        List<String> lines = getData("data.txt");
//        log(lines.size());

        List<String> tc = getData("tc.txt");

//        String wholefile = getDataAsOneString("data.txt");

//        parse(wholefile);

//        Long testCount = p1(parse(tc));
//        log("TestCount:" + testCount);
//
//        Long res1 = p1(parse(lines));
//        log("Result1:" + res1);
//
//        Double testCount2 = p2(parse(tc));
        log("TestCount2:" + part02(parse(tc)));

//        Double res2 = p2(parse(lines));
//        log("Result2:" + res2);
        log("Result2:" + part02(parse(lines)));
    }

    protected static List<Integer> parse(List<String> input) {

        List<Integer> list = input.stream().map(Integer::valueOf).sorted().collect(Collectors.toList());


        //list.forEach(System.out::println);

        return list;
    }


    protected static List<String> parse(String wholeFile) {
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

    protected static Long p1(final List<Integer> list) {
        Long sumone = 0L;
        Long sumtwo = 0L;
        Long sumthree = 1L;

        int prev = 0;

        for (int i = 0; i < list.size(); i++) {
            int diff = list.get(i) - prev;
            switch (diff) {
                case 1:
                    sumone++;
                    break;
                case 2:
                    sumtwo++;
                    break;
                case 3:
                    sumthree++;
                    break;
                default:

            }
            prev = list.get(i);

        }
        log("Sum1=" + sumone);
        log("Sum3=" + sumthree);

        return sumone * sumthree;
    }


    protected static Double p2(List<Integer> list) {
        List<Pair<Integer, Integer>> cache = new ArrayList<>();
        int max = list.get(list.size() - 1) + 3;
        list.add(0);


        List<Integer> newList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        log(newList);
        int prev = max;
        int i = 0;
        for (; i < newList.size(); i++) {
            int paths = 0;
            int curr = newList.get(i);
            if (curr == 12) {
                log(12);
            }
            int diff = prev - newList.get(i);
            if (diff == 3) {
                paths = 1;
//                cache.add(Pair.of(i, 1));
                prev = curr;

            } else if (diff == 2) {
                paths++;
                if (i + 1 < newList.size() && (prev - newList.get(i + 1)) == 3) {
                    paths++;
                    prev = newList.get(i + 1);
                    i = i + 1;
                } else {
                    prev = curr;
                }
//                cache.add(Pair.of(i, paths));
            } else if (diff == 1) {
                paths++;
                if (i + 1 < newList.size() && (prev - newList.get(i + 1)) == 3) {
                    paths++;
                    prev = newList.get(i + 1);
                    i = i + 1;
                } else if (i + 1 < newList.size() && (prev - newList.get(i + 1)) == 2) {
                    paths++;
                    if (i + 2 < newList.size() && (prev - newList.get(i + 2)) == 3) {
                        paths++;
                        paths++;
                        prev = newList.get(i + 2);
                        i = i + 2;
                    } else {
                        prev = newList.get(i + 1);
                        i = i + 1;
                    }

                } else {
                    prev = curr;
                }
            }

            cache.add(Pair.of(prev, Integer.valueOf(paths)));

        }

        log(cache);
        Long routes = cache.stream().filter(p -> p.getRight() != 1).
                map(p -> p.getRight()).mapToLong(Long::valueOf).reduce(0L, (a, b) -> a + b);
        int count1 = 0;
        int count2 = 0;
        int count4 = 0;
        for (Pair<Integer, Integer> p : cache) {
            switch (p.getRight()) {
                case 1:
                    count1++;
                    break;
                case 2:
                    count2++;
                    break;
                case 4:
                    count4++;
                    break;
                default:
            }
        }
        log(count1);
        log(count2);
        log(count4);
        log((Math.pow(4, count4)) * (Math.pow(2, count2)));

//        4
//        8
//        4
//        2
//        8
//        8
        log("test2==>"+((Math.pow(16, 3))+(Math.pow(4,2)) + (Math.pow(2, 1))));


        return Math.pow(4, count4) * (Math.pow(2, count2)) * (Math.pow(1, count1));
    }


    public static long part02(List<Integer> data) {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(0);
        for(int d : data) {
            tmp.add(d);
        }
        Collections.sort(tmp, Collections.reverseOrder());
        long m = tmp.get(0) + 3;
        Map<Long,Long> dist  = new HashMap<>();
        dist.put(m,1L);
        for(long v : tmp) {
            long d1 = dist.getOrDefault(v+1,0L);
            long d2 = dist.getOrDefault(v+2,0L);
            long d3 = dist.getOrDefault(v+3,0L);
            dist.put(v,(d1+d2+d3));
        }
        return dist.get(0L);

    }

}
