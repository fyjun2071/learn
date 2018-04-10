package learn.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSortTest {

    @Test
    public void quickSort() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        dealPivot(arr, left, right);

        int pivot = right - 1;
        int i = left;
        int j = pivot;
        while (true) {
            while (arr[++i] < arr[pivot]) {
            }
            while ((j > left) && (arr[--j] > arr[pivot])) {
            }

            if (i < j) {
                swap(arr, i, j);
            } else {
                break;
            }
        }

        if (i < pivot) {
            swap(arr, i, pivot);
        }

        quickSort(arr, left, i - 1);
        quickSort(arr, i, right);
    }

    private void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }

        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }

        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }

        swap(arr, mid, right - 1);
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
