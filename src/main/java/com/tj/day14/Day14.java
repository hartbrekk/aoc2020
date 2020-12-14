package com.tj.day14;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tj.util.Utils.getData;
import static com.tj.util.Utils.log;

public class Day14 {
    public static void main(String[] args) {
        List<String> lines = getData("data.txt");
//        log(lines.size());

        List<String> tc = getData("tc.txt");

//        String wholefile = getDataAsOneString("data.txt");

//        parse(wholefile);

//        long testCount = parse(tc);
//        log("TestCount:" + testCount);
//
//        long res1 = parse(lines);
//        log("Result1:" + res1);

        long testCount2 = parse2(tc);
        log("TestCount:" + testCount2);

        long res2 = parse2(lines);
        log("Result1:" + res2);
    }

    static int inst(String st) {
        if (st.startsWith("mask")) {
            return -1;
        }
        if (st.startsWith("mem[")) {
            return Integer.valueOf(st.substring(4, st.length() - 1));
        }
        return -2;
    }

    protected static long parse(List<String> input) {
        long[] memory = new long[65536];
        List<Pair<Integer, String>> insts = input.stream()
                .map(line -> {
                    String[] tokens = line.split(" = ");
                    return Pair.of(inst(tokens[0]), tokens[1]);

                })
                .collect(Collectors.toList());
        insts.forEach(System.out::println);


        String mask = "";
        for (int i = 0; i < insts.size(); i++) {
            Integer inst = insts.get(i).getLeft();
            String value = insts.get(i).getRight();
            if (inst == -1) {
                mask = value;
                continue;
            }
            long val = Long.valueOf(value);
            long newVal = mask(val, mask);
            memory[inst] = newVal;
            log(newVal);

        }
        long sum = 0;
        for (int i = 0; i < 65536; i++) {
            sum += memory[i];
            if (memory[i] > 0) {

//                System.out.print(memory[i]);
            }
        }
        log("\n");


        return sum;
    }

    public static boolean isSet(int flags, int mask) {
        return (flags & mask) == mask;
    }

    static long mask(long input, String bitmask) {

        StringBuilder binInput = new StringBuilder(StringUtils.leftPad(Long.toBinaryString(input), 36, "0"));
//        log(binInput);
        log(binInput.length());
        for (int i = 0; i < 36; i++) {
            char ch = bitmask.charAt(i);
            if (ch == 'X') {
                continue;
            }
            binInput.setCharAt(i, ch);
        }

        return Long.valueOf(binInput.toString(), 2);

    }


    protected static long parse2(List<String> input) {
        Map<Long, Long> memoryMap = new HashMap<>(2147483647);


        List<Pair<Integer, String>> insts = input.stream()
                .map(line -> {
                    String[] tokens = line.split(" = ");
                    return Pair.of(inst(tokens[0]), tokens[1]);

                })
                .collect(Collectors.toList());


        String mask = "";
        for (int i = 0; i < insts.size(); i++) {
            Integer inst = insts.get(i).getLeft();
            String value = insts.get(i).getRight();
            if (inst == -1) {
                mask = value;
                continue;
            }
            long val = Long.valueOf(value);
            mask2(memoryMap, inst, mask, val);

        }
        long sum = 0;
        for (Long l : memoryMap.values()) {
            sum += l;
        }


        return sum;
    }

    static void mask2(Map<Long, Long> memory, int input, String bitmask, final long value) {

        StringBuilder binInput = new StringBuilder(StringUtils.leftPad(Long.toBinaryString(input), 36, "0"));

        List<StringBuilder> pool = new ArrayList<>();

        for (int i = 0; i < 36; i++) {
            char ch = bitmask.charAt(i);
            if (ch == '1') {
                binInput.setCharAt(i, ch);
            }
        }
        pool.add(binInput);
        for (int i = 35; i >= 0; i--) {
            char ch = bitmask.charAt(i);
            if (ch != 'X') {
                continue;
            }
            List<StringBuilder> newpool = new ArrayList<>();
            for (final StringBuilder s : pool) {
                StringBuilder sb0 = new StringBuilder(s);
                sb0.setCharAt(i, '0');
                newpool.add(sb0);
                StringBuilder sb1 = new StringBuilder(s);
                sb1.setCharAt(i, '1');
                newpool.add(sb1);

            }

            pool = newpool;
        }
        for (final StringBuilder s : pool) {
            Long index = Long.valueOf(s.toString(), 2);
            memory.put(index, value);
        }

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

    protected static Integer p1(final List<BigInteger> list) {
        Integer sum = 0;
        return sum;
    }

    protected static Integer p2(List<BigInteger> list) {
        Integer sum = 0;
        return sum;
    }


}
