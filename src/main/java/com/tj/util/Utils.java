package com.tj.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static List<String> getData(String path) {
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(Utils.class.getClassLoader()
                .getResource(path).toURI()))) {
            list = stream.collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getDataAsOneString(String path) {
        String line = null;
        try {
            line = new String(Files.readAllBytes(Paths.get(Utils.class.getClassLoader()
                    .getResource(path).toURI())), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static Stream<String> getStream(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(Utils.class.getClassLoader()
                .getResource(path).toURI()))) {
            return stream;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void log(Object msg){
        System.out.println(msg);
    }

    public static void err(Object msg){
        System.err.println(msg);
    }

    public static void pst(Throwable t){
        t.printStackTrace();
    }

}
