package com.tj;

import java.util.stream.IntStream;

public class FirstClass {

    public static void main(String args[]) {
        int[] arr = {1150,
                1579,
                1361,
                1319,
                1201,
                1253,
                1806,
                1783,
                1164,
                1772,
                1920,
                1428,
                1918,
                245,
                1504,
                1952,
                1057,
                1977,
                704,
                1119,
                1971,
                1200,
                1650,
                1795,
                1877,
                1932,
                1811,
                1981,
                1803,
                1366,
                1580,
                1986,
                1976,
                1063,
                1895,
                1143,
                1991,
                1061,
                1855,
                1947,
                1134,
                1800,
                1898,
                1778,
                1964,
                1949,
                1103,
                1770,
                1321,
                2005,
                1758,
                1181,
                1140,
                1873,
                1946,
                1540,
                1909,
                1710,
                1705,
                1313,
                1196,
                1084,
                1870,
                1610,
                1708,
                1810,
                1133,
                1375,
                1264,
                1921,
                1624,
                41,
                1899,
                1226,
                1757,
                1978,
                1485,
                1385,
                1526,
                1653,
                1130,
                1223,
                1577,
                1912,
                1894,
                276,
                954,
                1269,
                1769,
                1924,
                93,
                1165,
                1812,
                1092,
                1402,
                1284,
                1903,
                1884,
                1581,
                1887,
                1963,
                1983,
                1233,
                1445,
                1974,
                1956,
                1691,
                1954,
                2000,
                1469,
                1875,
                955,
                1334,
                1116,
                1700,
                1818,
                1790,
                1704,
                1901,
                1072,
                1848,
                1990,
                1724,
                1719,
                1638,
                1311,
                1474,
                1837,
                1801,
                1929,
                1791,
                1317,
                1643,
                1632,
                1813,
                1488,
                1129,
                1998,
                1771,
                1793,
                1074,
                1826,
                1935,
                1462,
                1230,
                1797,
                1878,
                1751,
                1993,
                1437,
                1967,
                1844,
                1438,
                1969,
                1175,
                1823,
                1124,
                1922,
                154,
                936,
                1117,
                1145,
                1308,
                1320,
                1767,
                1850,
                1809,
                1350,
                1820,
                1082,
                1597,
                1913,
                1766,
                1701,
                1294,
                1556,
                2006,
                1480,
                1953,
                1104,
                1861,
                1966,
                1248,
                1671,
                1955,
                1863,
                1202,
                1356,
                1842,
                2010,
                1288,
                1067,
                1576,
                1295,
                1760,
                1888,
                1639,
                1282,
                1633,
                1619};

        int first=0;
        int second=0;
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = 2020 - arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (diff == arr[j]) {
                    System.out.println("" + arr[i]);
                    System.out.println("" + arr[j]);
                    first=arr[i];
                    second=arr[j];
                    break;
                }
            }
        }
        System.out.println(first+second);
        System.out.println(first*second);
        find3Numbers(arr, arr.length, 2020);
    }

    static boolean  find3Numbers(int A[], int arr_size, int sum)
    {
        int l, r;

        // Fix the first element as A[i]
        for (int i = 0; i < arr_size - 2; i++) {

            // Fix the second element as A[j]
            for (int j = i + 1; j < arr_size - 1; j++) {

                // Now look for the third number
                for (int k = j + 1; k < arr_size; k++) {
                    if (A[i] + A[j] + A[k] == sum) {
                        System.out.print("Triplet is " + A[i] + ", " + A[j] + ", " + A[k]);
                        System.out.print("Triplet product is " + (A[i] * A[j] * A[k]));
                        return true;
                    }
                }
            }
        }

        // If we reach here, then no triplet was found
        return false;
    }
}

