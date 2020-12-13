package com.tj.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tj.util.Utils.getData;
import static com.tj.util.Utils.log;

public class Template {
    public static void main(String[] args) {
        List<String> lines = getData("data.txt");
//        log(lines.size());

        List<String> tc = getData("tc.txt");

//        String wholefile = getDataAsOneString("data.txt");

//        parse(wholefile);

        Integer testCount = parse(tc);
        log("TestCount:" + testCount);

        Integer res1 = parse(lines);
        log("Result1:" + res1);
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
