package com.cy.sort;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arrays = {9, 2, 5, 1, 3, 2, 9, 5, 2, 1, 8};
        mergeSort(arrays);
        System.out.println("Result is [" + arrayStr(arrays) + "]");
    }

    /**
     * 归并排序
     * todo 传入Compare自定义排序方式
     * @param arrays
     */
    public static void mergeSort(int[] arrays){
        if(arrays.length < 2){
            return;
        }
        mergeSort(arrays, 0, arrays.length - 1);
    }

    private static void mergeSort(int[] arrays, int start, int end) {
        if(start == end){
            return;
        }
        int mid = (start + end) / 2;
//        拆分左边
        mergeSort(arrays, 0, mid);
//        拆分右边
        mergeSort(arrays, mid + 1, end);
//        分久必合
        merge(arrays, start, mid, end);
    }

    private static void merge(int[] arrays, int start, int mid, int end) {
        int[] arrayL = Arrays.copyOfRange(arrays, start, mid + 1);
        int[] arrayR = Arrays.copyOfRange(arrays, mid + 1, end + 1);
        int leftIdx = 0, rightIdx = 0, arrayIdx = start;
//        分治排序，直到一端排完，剩下的交由下一步直接复制入数组
        while (leftIdx < arrayL.length && rightIdx < arrayR.length){
            if(arrayL[leftIdx] < arrayR[rightIdx]){
                arrays[arrayIdx++] = arrayL[leftIdx++];
            } else {
                arrays[arrayIdx++] = arrayR[rightIdx++];
            }
        }
//        如果左边还有剩余的时候，将左边的剩余的复制到数组中（剩下的都是大的，而且顺序都是上一轮排好的，且栈顶的左右数组长度最多为1）
        while (leftIdx < arrayL.length){
            arrays[arrayIdx++] = arrayL[leftIdx++];
        }
//        如上
        while (rightIdx < arrayR.length){
            arrays[arrayIdx++] = arrayR[rightIdx++];
        }
    }

    private static String arrayStr(int[] array){
        return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

}
