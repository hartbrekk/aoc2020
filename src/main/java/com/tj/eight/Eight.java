package com.tj.eight;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import com.tj.util.Utils;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;
import java.util.stream.Collectors;

import static com.tj.util.Utils.*;

public class Eight {

    public static void main(String[] args) {
        List<String> lines = getData("data.txt");
        log(lines.size());

        List<String> tc = getData("tc.txt");

//        String wholefile = getDataAsOneString("data.txt");

//        parse(wholefile);

        Integer testCount = p2(parse(tc));
        log("TestCount:" + testCount);

        Integer res1 = p2(parse(lines));
        log("Result1:" + res1);
//
//        Integer testCount2 = p2(parse2(tc));
//        log("TestCount:" + testCount2);
//
//        Integer res2 = p2(parse2(lines));
//        log("Result2:" + res2);
    }

    protected static List<Pair<String, Integer>> parse(List<String> list) {

        List<Pair<String, Integer>> output = new ArrayList<>();
        for (String line : list) {

        }

        output = list.stream().map(l -> l.split("\\s")).map(token -> Pair.of(token[0], Integer.parseInt(token[1]))).collect(Collectors.toList());
//        output.forEach(System.out::println);

        return output;
    }

//    protected static List<Pair<String, Integer>> parse(String wholeFile) {
//        List<Map<String, String>> map = new ArrayList<>();
//        //Preserve a new line for internal parsing***
//        String[] records = wholeFile.split("\r\n\r\n");
////        Arrays.stream(records).forEach(Utils::log);
//        int i=0;
//        for(String rec : records){
//            rec = rec.replaceAll("\r\n", "");
//            log(i+++":"+rec);
//        }
//
//
//        return map;
//    }

    protected static Integer p1(final List<Pair<String, Integer>> list) {
        List<MutableTriple<String, Integer, Boolean>> newlist = list.stream().map(pair -> new MutableTriple<>(pair.getLeft(), pair.getRight(), false)).collect(Collectors.toList());
        Integer sum = 0;
        boolean noloop = true;
        int index = 0;
        while (noloop && index < newlist.size()) {
            MutableTriple<String, Integer, Boolean> t = newlist.get(index);
            String inst = t.getLeft();
            Integer val = t.getMiddle();
            Boolean visited = t.getRight();

            if (visited) {
                log("Sum" + sum);
                noloop = false;
                log(newlist);
                return sum;
            }
            switch (inst) {
                case "jmp":
                    index = index + val;
                    break;
                case "acc":
                    sum += val;
                    index++;
                    break;
                case "nop":
                    index++;
                    break;
                default:
                    continue;
            }

            t.setRight(true);
        }
        log(newlist);
        return sum;
    }

    protected static Boolean isNoLoop(List<Pair<String, Integer>> list){
        List<MutableTriple<String, Integer, Boolean>> newlist = list.stream().map(pair -> new MutableTriple<>(pair.getLeft(), pair.getRight(), false)).collect(Collectors.toList());
        Integer sum = 0;
        boolean noloop = true;
        int index = 0;
        while (noloop && index < newlist.size()) {
            MutableTriple<String, Integer, Boolean> t = newlist.get(index);
            String inst = t.getLeft();
            Integer val = t.getMiddle();
            Boolean visited = t.getRight();

            if (visited) {
                log("Sum" + sum);
                noloop = false;
                log(newlist);
                return false;
            }
            switch (inst) {
                case "jmp":
                    index = index + val;
                    break;
                case "acc":
                    sum += val;
                    index++;
                    break;
                case "nop":
                    index++;
                    break;
                default:
                    continue;
            }

            t.setRight(true);
        }
        log(newlist);
        log("My sum "+sum);
        return true;
    }


    protected static Integer p2(List<Pair<String, Integer>> list) {
        List<Pair<String, Integer>> newlist = new ArrayList<>(list);
        int i = 0;
        for(Pair<String, Integer> pair : list){
            MutablePair<String, Integer> t = new MutablePair<>(pair.getLeft(), pair.getRight());
            String inst = pair.getLeft();
            switch (inst) {
                case "jmp":
                    t.setLeft("nop");
                    break;
                case "nop":
                    t.setLeft("jmp");
                    break;
                default:
                    break;
            }
            newlist.set(i, t);
            if(isNoLoop(newlist)){
                log("My index "+i);
                break;
            }
            newlist.set(i, pair);
            i++;

        }

        return 0;
    }


}
