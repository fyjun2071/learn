package learn.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 函数式编程
 */
public class Lambda {

    public void map() {
        List<String> list = Stream.of("aaaa", "bbbtest", "ccctest", "ddd", "ffftest")
                .filter(s -> s.endsWith("test"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(list);
    }

    public void maxmin() {
        String r = Stream.of("aaaa", "bbbtest", "ccctest", "ddd", "ffffftest")
                .min(Comparator.comparing(s -> s.length()))
                .get();

        System.out.println(r);
    }

    public void reduce() {
        Integer n = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(0, (acc, element) -> acc + element);
        System.out.println(n);
    }

    public void forEach() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).forEach(n -> System.out.println(n));
    }

    public static void main(String[] args){
        Lambda l = new Lambda();
        l.forEach();
    }

}
