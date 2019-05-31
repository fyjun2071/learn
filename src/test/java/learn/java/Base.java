package learn.java;

import org.junit.Test;

import java.util.HashMap;

public class Base {

    @Test
    public void test1() {
        int a = 1000;
        System.out.println(a == new Integer(1000));
        System.out.println(new Integer(1000).equals(1000));
        System.out.println(new Integer(1000).equals(new Integer(1000)));

        System.out.println(new Integer(10) == new Integer(10));
        System.out.println(new Boolean(true) == new Boolean(true));
        System.out.println(new String("abc") == new String("abc"));

        Integer n = Integer.MAX_VALUE + 1;
        System.out.println(n);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void hashMap() {
        HashMap<Integer, Object> map = new HashMap<>(2);
        map.put(1, 1);
        map.put(17, 1);
        map.put(33, 1);
        System.out.println(map);
    }

    @Test
    public void test2() {
        System.out.println(8 << 1);
        System.out.println(8 >> 1);
        System.out.println(8 >>> 1);
        System.out.println(-8 >>> 1);
        System.out.println(-8 >> 1);
    }
}
