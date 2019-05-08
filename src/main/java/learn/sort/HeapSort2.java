package learn.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort2 {

    public static void main(String[] args) {
        int[] arr = {5, 8, 4, 6, 0, 7, 3, 1, 2, 9, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] a) {

        // 构建大顶堆
        for (int k = a.length / 2 - 1; k >= 0; k--) {
            sink(a, k, a.length);
        }

        // 逐个将堆顶的最大值放到队尾
        for (int n = a.length - 1; n > 0; n--) {
            exch(a, 0, n);
            sink(a, 0, n);
        }
    }

    private static void sink(int[] a, int k, int length) {
        for (int j = 2 * k + 1; j < length; j = 2 * j + 1) {
            if (j + 1 < length && a[j] < a[j + 1]) {
                j++;
            }

            if (a[k] > a[j]) {
                break;
            }

            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
