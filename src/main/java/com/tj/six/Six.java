package com.tj.six;

import org.apache.commons.lang3.StringUtils;
import com.tj.util.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Six {

    public static void main(String[] args) {
        List<String> lines = Utils.getData("data.txt");
        System.out.println(lines.size());
        List<String> tc = Utils.getData("tc.txt");

        Integer testCount = p1(parse(tc));
        System.out.println("TestCount:" + testCount);

        Integer res1 = p1(parse(lines));
        System.out.println("Result1:" + res1);


        Integer testCount2 = p2(parse2(tc));
        System.out.println("TestCount:" + testCount2);

        Integer res2 = p2(parse2(lines));
        System.out.println("Result2:" + res2);


//        Integer res2 = p1(transform(parse(lines)));
//        System.out.println("Result2:" + res2);
    }

    protected static List<Set<String>> parse(List<String> list) {

        List<Set<String>> l3 = new ArrayList<>();

        Set<String> set = new TreeSet<>();
        for (String line : list) {
            if (StringUtils.isBlank(line.trim())) {
                l3.add(set);
                set = new TreeSet<>();
            }

            for (int i = 0; i < line.length(); i++) {
                set.add("" + line.charAt(i));
            }

        }
        return l3;
    }
//    protected static List<Set<String>> transform(List<Tuple> list) {
//
//
//    }
//

    protected static Integer p1(List<Set<String>> list) {
        Integer count = 0;
        count = list.stream().map(s -> s.size()).collect(Collectors.summingInt(Integer::intValue));
        ;
        return count;
    }


    protected static List<List<String>> parse2(List<String> list) {

        List<List<String>> l3 = new ArrayList<>();

        List<String> l0 = new ArrayList<>();
        for (String line : list) {
            if (StringUtils.isBlank(line.trim())) {
                l3.add(l0);
                l0 = new ArrayList<>();
            } else {
                l0.add(line);
            }





        }
        return l3;
    }


    protected static Integer p2(List<List<String>> list) {
        Integer count = 0;

        for(List<String> subList : list){
            Set<Character> first = populate(subList.get(0));
            for(int i =1; i< subList.size(); i++){
                first.retainAll(populate(subList.get(i)));
            }


            count += first.size();
        }
        ;
        return count;
    }

    public List<String> intersection(List<java.lang.String> list1, List<java.lang.String> list2) {
        List<String> list = new ArrayList<String>();

        for (String t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
//    protected static Integer p2(List<Set<String>> list) {
//        return list.stream().min(Integer::compare).get();
//    }


    static Set<Character> populate(String s1){
        Set<Character> h1 = new HashSet<Character>();
        for(int i = 0; i < s1.length(); i++)
        {
            h1.add(s1.charAt(i));
        }
        return h1;
    }
}
