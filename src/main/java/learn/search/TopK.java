package learn.search;

/**
 * 快速排序解决topk问题
 */
public class TopK {

    public static void main(String[] args) {
        int[] arr = {6, 7, 0, 9, 5, 4, 3, 2, 1, 8};
        int k = 4;
        int idx = arr.length - k;
        search(arr, 0, arr.length - 1, idx);
        System.out.println(idx + ":" + arr[idx]);
    }

    private static void search(int[] a, int l, int r, int idx) {
        if (l >= r) return;
        int p = partition(a, l, r);

        if (p > idx) {
            search(a, l, p - 1, idx);
        } else if (p < idx) {
            search(a, p + 1, r, idx);
        } else {
            return;
        }
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
