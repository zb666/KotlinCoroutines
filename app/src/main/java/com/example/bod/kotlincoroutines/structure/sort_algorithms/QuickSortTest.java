package com.example.bod.kotlincoroutines.structure.sort_algorithms;

/**
 * @ClassName: QuickSortTest
 * @Description:
 * @CreateDate: 2020/2/1
 */
public class QuickSortTest {

    public static void main(String[] args) {
        int[] array = {1, 5, 3, 2, 8, 6, 4};
        startQuickSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.println("QuickResult: "+i);
        }
    }

    private static void startQuickSort(int[] array, int start, int end) {
        if (start >= end) return;
        int low = start;
        int high = end;
        int pivot = array[low];
        while (low < high) {
            while (low < high && array[high] >= pivot) {
                high--;
            }
            array[low] = array[high];//将<pivot的数字放在地位
            while (low < high && array[low] <= pivot) {
                low++;
            }
            array[high] = array[low];//将>pivot的数放在高位
        }
        array[low] = pivot;
        startQuickSort(array,start,low-1);
        startQuickSort(array,low+1,end);
    }

    static int _partition(int[] array, int low, int high) {
        int value = array[low];//初始化基准数
        int j = low;
        for (int i = low + 1; i <= high; i++) {
            if (array[i] < value) { //小于基准数
                swap(array[j + 1], array[i]);
                j++;//交换完成后，光标后移
            }
        }
        swap(array[j], array[low]);
        return j;
    }

    static void _quickSort(int[] array, int low, int high) {
        if (low >= high) return;
        int p = _partition(array, low, high);
        _quickSort(array, low, p - 1);
        _quickSort(array, p + 1, high);
    }

    static void quickSort(int[] array, int low, int high) {
        _quickSort(array, low, high);
    }

    static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

}
