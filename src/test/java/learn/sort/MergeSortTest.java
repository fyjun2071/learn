package learn.sort;

import org.junit.Test;

import java.util.Arrays;

public class MergeSortTest {

    @Test
    public void sort() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    private void sort(int[] arr) {
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int index = 0;
        while ((i <= mid) && (j <= right)) {
            if ((arr[i] > arr[j])) {
                temp[index++] = arr[j++];
            } else {
                temp[index++] = arr[i++];
            }
        }

        while (i <= mid) {
            temp[index++] = arr[i++];
        }

        while (j <= right) {
            temp[index++] = arr[j++];
        }

        index = 0;
        while (left <= right) {
            arr[left++] = temp[index++];
        }
    }

}
