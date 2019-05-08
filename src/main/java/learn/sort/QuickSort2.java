package learn.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort2 {

    public static void main(String[] args) {
        int[] arr = {6, 7, 0, 9, 5, 4, 3, 2, 1, 8};
        sort(arr);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    private static void sort(int[] a) {
        // TODO shuffle 对数组洗牌，随机顺序，消除对输入的依赖
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int l, int r) {
        if (l >= r) return;
        int p = partition(a, l, r);
        sort(a, l, p - 1);
        sort(a, p + 1, r);
    }

    /**
     * 以第一个数为枢纽值切分
     * @param a
     * @param l
     * @param r
     * @return
     */
    private static int partition(int[] a, int l, int r) {
        int t = a[l];
        int i = l;
        int j = r + 1;

        while (true) {
            while (a[++i] < t && i < r) {}
            while (a[--j] > t && j > l) {}

            if (i >= j) break;
            swap(a, i, j);
        }

        swap(a, l, j);
        return j;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
