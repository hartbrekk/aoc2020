package com.tj.day13;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class GFG {

    // k is size of num[] and rem[].  Returns the smallest
    // number x such that:
    //  x % num[0] = rem[0],
    //  x % num[1] = rem[1],
    //  ..................
    //  x % num[k-2] = rem[k-1]
    // Assumption: Numbers in num[] are pairwise coprime
    // (gcd for every pair is 1)
    static long findMinX(int num[], int rem[], int k, long x) {


        // As per the Chinese remainder theorem,
        // this loop will always break.
        while (true) {
            // Check if remainder of x % num[j] is
            // rem[j] or not (for all j from 0 to k-1)
            int j;
            for (j = 0; j < k; j++)
                if (x % num[j] != rem[j])
                    break;

            // If all remainders matched, we found x
            if (j == k)
                return x;

            // Else try next numner
            x++;
        }

    }
//    static long findMinX(List<Integer> num, List<Integer> rem[], int k, int x) {
//        return findMinX( num, rem,  k,x);
//    }

    static long findMinX(List<Pair<Integer, Integer>> list, long x) {
        list.stream().map(p -> p.getLeft()).collect(Collectors.toList());

        return findMinX(toIntArray(list.stream().map(p -> p.getLeft()).collect(Collectors.toList())),
                toIntArray(list.stream().map(p -> p.getLeft()).collect(Collectors.toList())),
                list.size(),
                x
        );
    }

    static int[] toIntArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = list.get(i);
        return ret;
    }
}
