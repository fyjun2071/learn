package learn.sort;

/**
 * 归并排序
 */
public class MergeSort2 {

    /** 与原数组同样大小的临时数组 */
    private static int[] aux;

    public static void sort(int[] a) {
        aux = new int[a.length];
        sort(a, 0, a.length - 1);
    }

    /**
     * 自顶向下归并
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    /**
     * 自底向上归并
     * @param a
     */
    private static void sort2(int[] a) {
        aux = new int[a.length];
        int n = a.length;
        for (int s = 1; s < n; s = s + s) {
            for (int lo = 0; lo < n - s; lo += s + s) {
                merge(a, lo, lo + s - 1, Math.min(lo + s + s - 1, n - 1));
            }
        }
    }

    private static void merge(int[] a, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

}
