package learn.string;

import java.util.Arrays;

/**
 * 三向字符串快速排序
 */
public class Quick3String {

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }

        // 以第一个数为枢纽值切分
        int lt = lo;
        int gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) {
                swap(a, lt++, i++);
            } else if (t > v) {
                swap(a, i, gt--);
            } else {
                i++;
            }
        }

        sort(a, lo, lt - 1, d);
        if (v > 0) {
            // 下一个字符排序
            sort(a, lt, gt, d + 1);
        }
        sort(a, gt + 1, hi, d);
    }

    private static int charAt(String s, int d) {
        return d < s.length() ? s.charAt(d) : -1;
    }

    private static void swap(String[] a, int i, int j) {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"she",
                                  "sells",
                                  "seashells",
                                  "by",
                                  "the",
                                  "seashore",
                                  "the",
                                  "shells",
                                  "she",
                                  "sells",
                                  "are",
                                  "surely",
                                  "seashells"
        };
        sort(a);
        Arrays.stream(a).forEach(System.out::println);
    }

}
