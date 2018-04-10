package learn.sort;

import org.junit.Test;

import java.util.Arrays;

public class SimpleSortTest {

    @Test
    public void bubbleSort() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        bubbleSort(arr);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    @Test
    public void selectSort() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        selectSort2(arr);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    @Test
    public void insertSort() {
        int[] arr = {9, 2, 7, 4, 5, 6, 3, 8, 1};
        insertSort(arr);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    private void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }

            if (flag) {
                break;
            }

            System.out.println(i + ":" + Arrays.toString(arr));
        }
    }

    private void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int max = 0;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[max] < arr[j]) {
                    max = j;
                }
            }

            swap(arr, max, arr.length - 1 - i);
            System.out.println(i + ":" + Arrays.toString(arr));
        }
    }

    private void selectSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }

            if (min != i) {
                swap(arr, min, i);
            }

            System.out.println(i + ":" + Arrays.toString(arr));
        }
    }

    private void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j-1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }

            System.out.println(i + ":" + Arrays.toString(arr));
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
