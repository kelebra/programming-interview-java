package com.github.tkachuko.origramming.interview.search;

import java.util.List;

public class Search {

    /**
     * Finds index of first element occurrence in a sorted list
     *
     * @param list    sorted list
     * @param element to find
     * @return index of first element occurrence or -1
     */
    public static int indexOfInSorted(List<Integer> list, int element) {
        int start = 0, end = list.size() - 1, result = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int current = list.get(mid);
            if (current > element) end = mid - 1;
            else if (current < element) start = mid + 1;
            else {
                result = mid;
                end = mid - 1;
            }
        }
        return result;
    }

    /**
     * Finds local minima in list ascending from left to right and descending from right to left
     *
     * @param list of integers
     * @return local minima
     */
    public static int localMinima(List<Integer> list) {
        int start = 0, end = list.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid == 0 || mid == list.size() - 1) return -1;

            int minima = list.get(mid);
            int left = list.get(mid - 1);
            int right = list.get(mid + 1);

            if (minima <= left && minima <= right) return minima;
            else if (minima < right) end = mid;
            else if (minima > left) start = mid;
        }
        return -1;
    }
}
