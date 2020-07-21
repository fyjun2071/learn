package learn.search;

import java.util.Arrays;

/**
 * 堆排序解决topk问题
 */
public class TopK2 {

    public static void main(String[] args) {
        int[] arr = {6, 7, 0, 9, 5, 4, 3, 2, 1, 8};
        int k = 3;
        search(arr, arr.length - k + 1);
        System.out.println(arr[0]);
        System.out.println(Arrays.toString(arr));
    }

    public static void search(int[] a, int m) {
        // 构建大顶堆
        for (int k = a.length / 2 - 1; k >= 0; k--) {
            sink(a, k, a.length);
        }

        // 逐个将堆顶的最大值放到队尾
        for (int n = a.length - 1; n >= m; n--) {
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
