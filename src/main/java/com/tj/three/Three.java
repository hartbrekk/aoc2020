package com.tj.three;

import com.tj.util.Utils;

import java.math.BigInteger;
import java.util.List;

public class Three {


    public static void main(String[] args) {
        List<String> lines = Utils.getData("data.txt");
//        List<Tuple> list = lines.stream().map(a -> {
//            Tuple t = new Tuple();
//            String[] tokens = a.split(" ");
//            String[] b = tokens[0].split("-");
//            int min = Integer.parseInt(b[0]);
//            int max = Integer.parseInt(b[1]);
//            char chr = tokens[1].split(":")[0].charAt(0);
//            t.first = b[0];
//            t.second = b[1];
//            t.third = tokens[1].split(":")[0];
//            return t;
//        }).collect(Collectors.toList());
//        list.forEach(System.out::println);
        List<String> tc = Utils.getData("tc.txt");

//lines = Arrays.asList(s);
        lines=tc;
int linesize = 11;
//        Integer res0 = p1(Arrays.asList(s), 31);
//        System.out.println("P1=" + (res0));
        Integer res1 = p2(lines, linesize, 1, 1);
        System.out.println("P2=" + (res1));
        Integer res2 = p2(lines, linesize, 3, 1);
        System.out.println("P2=" + (res2));
        Integer res3 = p2(lines, linesize, 5, 1);
        System.out.println("P2=" + (res3));
        Integer res4 = p2(lines, linesize, 7, 1);
        System.out.println("P2=" + (res4));
        Integer res5 = p2(lines, linesize, 1, 2);
        System.out.println("P2=" + (res5));
        BigInteger  b1 = BigInteger.valueOf(res1.intValue());
        BigInteger  b2 = BigInteger.valueOf(res2.intValue());
        BigInteger  b3 = BigInteger.valueOf(res3.intValue());
        BigInteger  b4 = BigInteger.valueOf(res4.intValue());
        BigInteger  b5 = BigInteger.valueOf(res5.intValue());
        BigInteger p3 = (b1.multiply(b2).multiply(b3).multiply(b4).multiply(b5));
        System.out.println("P3=" + p3);
    }

    protected static Integer p1(List<String> list, int linesize) {
        int tree =0;

        for(int i=0, j=0;j<list.size();j++, i=(i+3)%linesize){
            String s = list.get(j);
            char stop = s.charAt(i);
            System.out.println(s);
            System.out.println(stop);
            if(stop =='#') tree++;
            ;


        }
        return tree;
    }

    protected static Integer p2(List<String> list, int linesize, int x, int y) {
        int tree =0;

        for(int i=0, j=0;j<list.size();j=j+y, i=(i+x)%linesize){
            String s = list.get(j);
            char stop = s.charAt(i);
//            System.out.println(s);
//            System.out.println(stop);
            if(stop =='#') tree++;
        }
        return tree;
    }

}
