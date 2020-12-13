package com.tj.nine;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tj.util.Utils.getData;
import static com.tj.util.Utils.log;
import static com.tj.util.Utils.*;

public class Nine {
    public static void main(String[] args) {
        List<String> lines = getData("data.txt");
//        log(lines.size());

        List<String> tc = getData("tc.txt");

//        String wholefile = getDataAsOneString("data.txt");

//        parse(wholefile);

        BigInteger testCount = parse(tc, 5);
        log("TestCount:" + testCount);

        BigInteger res1 = parse(lines, 25);
        log("Result1:" + res1);
//
//        Integer testCount2 = p2(parse2(tc));
//        log("TestCount:" + testCount2);
//
//        Integer res2 = p2(parse2(lines));
    }

    protected static BigInteger parse(List<String> input, int chunksize) {

        List<BigInteger> list = input.stream().map(line -> new BigInteger(line)).collect(Collectors.toList());

        int i = chunksize;
        while (i < list.size() && isTwoSum(list.subList(i - chunksize, i), list.get(i))) {
            i++;
        }

        log("1.MY I=" + i);
        log("1.MY a[I]=" + list.get(i));

        isNSum(list.subList(0, i), list.get(i));


//        output.forEach(System.out::println);

        return list.get(i);
    }

    protected static boolean isTwoSum(List<BigInteger> list, BigInteger sum) {
//        BigInteger first=0;
//        BigInteger second=0;
        for (int i = 0; i < list.size() - 1; i++) {
            BigInteger diff = sum.subtract(list.get(i));
            for (int j = i + 1; j < list.size(); j++) {

                if (diff.equals(list.get(j))) {
//                    System.out.println("" + arr[i]);
//                    System.out.println("" + arr[j]);

                    return true;
                }
            }
        }
        return false;
    }

    protected static BigInteger isNSum(List<BigInteger> list, BigInteger sum) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            BigInteger total = list.get(i);

            for (int j = i + 1; j < list.size(); j++) {
                BigInteger sumJ = list.get(j);
                total = total.add(sumJ);
                if (total.equals(sum)) {
                    a = i;
                    b = j;
                    log("2. My I " + i);
                    log("2. My J " + j);

                    List<BigInteger> sub = list.subList(a, b + 1);
                    BigInteger min = sub.stream().min(BigInteger::compareTo).get();
                    BigInteger max = sub.stream().max(BigInteger::compareTo).get();
                    log("final answer=" + (min.add(max)));
                    return (min.add(max));
                } else if (total.compareTo(sum) < 0) {
                    continue;
                } else {
                    break;
                }

            }
        }

        return BigInteger.ZERO;
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
