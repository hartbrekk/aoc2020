package com.tj.four;

import org.apache.commons.lang3.StringUtils;
import com.tj.util.Tuple;
import com.tj.util.Utils;

import java.util.*;

public class Four {
    public static void main(String[] a) {
        List<String> lines = Utils.getData("data.txt");
        System.out.println(lines.size());
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
        parse(tc);
        Integer testCount1 = p1(parse(tc));
        Integer count1 = p1(parse(lines));

        System.out.println("TestCount1:" + testCount1);
        System.out.println("Result1:" + count1);
    }

    protected static List<Map<String, String>> parse(List<String> list) {
        List<Tuple> l2 = new ArrayList<>();
        List<Map<String, String>> l3 = new ArrayList<>();

        Tuple obj = new Tuple();
        Map<String, String> map = new TreeMap<>();
        for (String line : list) {
            if (StringUtils.isBlank(line.trim())) {
                l2.add(obj);
                l3.add(map);
                obj = new Tuple();
                map = new TreeMap<>();

            }

            String[] tokens = line.trim().split(" ");
            for (String token : tokens) {
                if (StringUtils.isBlank(token.trim())) {
                    continue;
                }
                String[] keyValue = token.trim().split(":");
//                if (!StringUtils.isBlank(keyValue[0]) && !StringUtils.isBlank(keyValue[1])) {
                if (!StringUtils.isBlank(keyValue[0])) {
                    map.put(keyValue[0].toLowerCase(), keyValue[1]);
                }

            }

        }

        return l3;
    }

    /**
     * *byr (Birth Year)
     * iyr (Issue Year)
     * eyr (Expiration Year)
     * hgt (Height)
     * hcl (Hair Color)
     * ecl (Eye Color)
     * pid (Passport ID)
     * cid (Country ID)
     *
     * @param list
     * @return
     */
    protected static Integer p1(List<Map<String, String>> list) {
        System.out.println("Total Records=" + list.size());
        System.out.println("lst Record=" + list.get(list.size() - 1));
        Set<String> colors = new HashSet<>();
        colors.add("amb");
        colors.add("blu");
        colors.add("brn");
        colors.add("gry");
        colors.add("grn");
        colors.add("hzl");
        colors.add("oth");

        int validCount = 0;
        for (Map<String, String> map : list) {
            boolean valid = false;
            if (map.containsKey("byr") &&
                    map.containsKey("iyr") &&
                    map.containsKey("eyr") &&
                    map.containsKey("hgt") &&
                    map.containsKey("hcl") &&
                    map.containsKey("ecl") &&
                    map.containsKey("pid")
            ) {
                valid = true;
                Integer byr = Integer.parseInt(map.get("byr"));
                Integer iyr = Integer.parseInt(map.get("iyr"));
                Integer eyr = Integer.parseInt(map.get("eyr"));
                String hgtstr = map.get("hgt");
                String hclstr = map.get("hcl");
                String eclstr = map.get("ecl");
                String pidstr = map.get("pid");

                if (byr < 1920 || byr > 2002) {
                    valid = false;
                }
                if (iyr < 2010 || iyr > 2020) {
                    valid = false;
                }
                if (eyr < 2020 || eyr > 2030) {
                    valid = false;
                }
                if (!(byr <= iyr && byr <= eyr && iyr <= eyr)) {
                    // valid = false;
                }

                if (hgtstr.lastIndexOf("cm") == hgtstr.length() - 2) {
                    String[] tokens = hgtstr.split("cm");
                    Integer hgt = Integer.parseInt(tokens[0]);
                    if (hgt < 150 || hgt > 193) {
                        valid = false;
                    }
                } else if (hgtstr.lastIndexOf("in") == hgtstr.length() - 2) {
                    String[] tokens = hgtstr.split("in");
                    Integer hgt = Integer.parseInt(tokens[0]);
                    if (hgt < 59 || hgt > 76) {
                        valid = false;
                    }

                } else {
                    valid = false;
                }

                if (hclstr.length() == 7 && hclstr.startsWith("#")) {
                    String colorcode = hclstr.substring(1);
                    if (!colorcode.matches("^[a-zA-Z0-9]*$")) {
                        valid = false;
                    }
                } else {
                    valid = false;
                }


                if (!colors.contains(eclstr.trim())) {
                    valid = false;
                }
                if (!(pidstr.length() == 9 && pidstr.matches("^[0-9]*$"))) {
                    valid = false;
                }


                if (valid) {
                    validCount++;
                }
            }

        }

        return validCount;
    }

}
