package learn.string;

import java.util.Arrays;

/**
 * 高位优先的字符串排序
 */
public class MSD {

    private static final int M = 0;    // 小数组的切换阈值, 小数组使用插入法排序
    private static int R = 26;         // 基数
    private static String[] aux;

    private static int charAt(String s, int d) {
        return d < s.length() ? s.charAt(d) - 97 : -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        System.out.println("************************************************************");
        System.out.println(Arrays.toString(a) + ":" + lo + ":" + hi + ":" + d);
        // 以第d个字符为键将a[lo]至a[hi]排序
        if (hi <= lo + M) {
            insertionSort(a, lo, hi, d);
            return;
        }

        int[] count = new int[R + 2];
        int r;
        // 计算频率
        for (int i = lo; i <= hi; i++) {
            r = charAt(a[i], d) + 2;
            count[r]++;
        }

        // 将频率转换为索引
        for (int i = 0; i < R + 1; i++) {
            count[i + 1] += count[i];
        }

        System.out.println(Arrays.toString(count));

        // 将元素分类
        for (int i = lo; i <= hi; i++) {
            r = charAt(a[i], d) + 1;
            aux[count[r]++] = a[i];
        }

        // 回写
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        // 递归以每个字符为键进行排序
        for (int i = 0; i < R; i++) {
            int l = lo + count[i];
            int h = lo + count[i + 1] - 1;
            if (l < h) {
                sort(a, l, h, d + 1);
            }

        }
    }

    public static void insertionSort(String[] a, int lo, int hi, int d) {

    }

    private static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
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
                "seashells",
                "zero"
        };
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
