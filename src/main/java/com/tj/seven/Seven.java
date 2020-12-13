package com.tj.seven;

import org.apache.commons.lang3.tuple.Pair;
import com.tj.util.Utils;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Seven {
    public static void main(String[] args) {
        List<String> lines = Utils.getData("data.txt");
        System.out.println(lines.size());
        List<String> tc = Utils.getData("tc.txt");

//        Integer testCount = p1(parse(tc));
//        System.out.println("TestCount:" + testCount);
//
//        Integer res1 = p1(parse(lines));
//        System.out.println("Result1:" + res1);


        BigInteger testCount2 = p2(parse2(tc));
        System.out.println("TestCount:" + testCount2);

        BigInteger res2 = p2(parse2(lines));
        System.out.println("Result2:" + res2);


//        Integer res2 = p1(transform(parse(lines)));
//        System.out.println("Result2:" + res2);
    }

    protected static Map<String, List<String>> parse(List<String> list) {

        Map<String, List<String>> map = new TreeMap<>();
        for (String line : list) {
            String[] tokens = line.split(" contain ");
            String key = tokens[0].replaceAll(" bags", "").replaceAll(" bag", "").trim();
            String[] subtokens = tokens[1].replace(".", "").split(", ");
            List<String> bags = Arrays.stream(subtokens).filter(bag -> !bag.contains("no other bags")).
                    map(bag -> bag.substring(2)).map(bag -> bag.replaceAll(" bags", "").replaceAll(" bag", "").trim())
                    .collect(Collectors.toList());
            map.put(key, bags);
        }

//        map.entrySet().forEach(System.out::println);

        return map;
    }

    protected static Integer p1(Map<String, List<String>> map) {
        Integer count = 0;

        for (String key : map.keySet()) {
            if (isPath(key, map)) {
                count++;
            }
        }
//        System.out.println("Count=" + count);
        return count;
    }

    private static final String SGOLD = "shiny gold";

    public static boolean isPath(String key, Map<String, List<String>> map) {
        if (key.equalsIgnoreCase(SGOLD)) {
            return false;
        }
        List<String> children = map.get(key);
        if (children == null) {
//            System.out.println(key);
//            System.out.println(children);

        }
        if (children.contains(SGOLD)) {
            return true;
        }
        for (String child : children) {
            if (isPath(child, map)) {
                return true;
            }
        }
        return false;
    }

    protected static Map<String, List<Pair<String, Integer>>> parse2(List<String> list) {

        Map<String, List<Pair<String, Integer>>> map = new TreeMap<>();
        for (String line : list) {
            String[] tokens = line.split(" contain ");
            String key = tokens[0].replaceAll(" bags", "").replaceAll(" bag", "").trim();
            String[] subtokens = tokens[1].replace(".", "").split(", ");


            List<Pair<String, Integer>> bags = Arrays.stream(subtokens).filter(bag -> !bag.contains("no other bags"))
                    .map(bag -> bag.replaceAll(" bags", "").replaceAll(" bag", "").trim())
                    .map(bag -> Pair.of(bag.substring(2), Integer.parseInt(bag.substring(0, 1)))).collect(Collectors.toList());
            map.put(key, bags);
        }

        map.entrySet().forEach(System.out::println);

        return map;
    }


    protected static BigInteger p2(Map<String, List<Pair<String, Integer>>> map) {
        BigInteger sum = BigInteger.ZERO;
        sum = getWeight(SGOLD, map);

        List<Pair<String, Integer>> children = map.get(SGOLD);


//        System.out.println("Count=" + count);

        return sum;
    }

    public static BigInteger getWeight(String key, Map<String, List<Pair<String, Integer>>> map) {

        List<Pair<String, Integer>> children = map.get(key);
        if (children == null || children.size() == 0) {
            return BigInteger.ZERO;
//            System.out.println(key);
//            System.out.println(children);

        }

        BigInteger sum = BigInteger.ZERO;
        for (Pair<String, Integer> child : children) {
            BigInteger childWeight = BigInteger.valueOf(child.getRight().intValue());
            sum = childWeight.multiply(getWeight(child.getLeft(), map)).add(childWeight).add(sum);
        }
        return sum;
    }
}
