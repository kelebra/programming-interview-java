package com.github.tkachuko.origramming.interview.arrays;

import com.github.tkachuko.origramming.interview.arrays.util.ThreeValues;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.tkachuko.origramming.interview.arrays.Arrays.*;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class ArraysTest {

    @Test
    @Parameters
    public void shouldSortArrayInDutchFlagManner(List<Integer> array, int pivotIndex) {
        int pivot = array.get(pivotIndex);

        dutchFlagSort(array, pivotIndex);

        int firstPivotIndex = array.indexOf(pivot);
        int lastPivotIndex = array.lastIndexOf(pivot);
        boolean allLessAreOnTheLeft = array.subList(0, firstPivotIndex).stream().allMatch(i -> i < pivot);
        boolean allPivotsAreTogether = array.subList(firstPivotIndex, lastPivotIndex).stream().allMatch(i -> i == pivot);
        boolean allGreaterAreOnTheRight = array.subList(lastPivotIndex + 1, array.size()).stream().allMatch(i -> i > pivot);
        assertTrue(allLessAreOnTheLeft && allPivotsAreTogether && allGreaterAreOnTheRight);
    }

    public Object parametersForShouldSortArrayInDutchFlagManner() {
        return new Object[]{
                new Object[]{asList(1, 3, 452, 1, 1, 2, 3, 1, 2, 3), 0},
                new Object[]{asList(1, 1, 1, 1), 0},
                new Object[]{asList(1, 2, 1, 3), 0},
                new Object[]{asList(1, 2, 3, 1), 1}
        };
    }

    @Test
    @Parameters
    public void shouldSortThreeValueArrayInASequentialOrder(List<ThreeValues> array) {
        threeValueSequentialSort(array);

        int firstIndex = array.lastIndexOf(ThreeValues.FIRST);
        int secondIndex = array.lastIndexOf(ThreeValues.SECOND);
        int thirdIndex = array.lastIndexOf(ThreeValues.THIRD);
        boolean firstValueIsSequential = firstIndex == -1 || array.subList(0, firstIndex).stream().allMatch(i -> i == ThreeValues.FIRST);
        boolean secondValueIsSequential = secondIndex == -1 || array.subList(firstIndex + 1, secondIndex).stream().allMatch(i -> i == ThreeValues.SECOND);
        boolean thirdValueIsSequential = thirdIndex == -1 || array.subList(secondIndex + 1, thirdIndex).stream().allMatch(i -> i == ThreeValues.THIRD);
        assertTrue(firstValueIsSequential && secondValueIsSequential && thirdValueIsSequential);
    }

    public Object parametersForShouldSortThreeValueArrayInASequentialOrder() {
        return new Object[]{
                new Object[]{Arrays.asList(
                        ThreeValues.FIRST, ThreeValues.SECOND,
                        ThreeValues.FIRST, ThreeValues.SECOND,
                        ThreeValues.THIRD, ThreeValues.FIRST)
                },
                new Object[]{Arrays.asList(
                        ThreeValues.THIRD, ThreeValues.FIRST,
                        ThreeValues.FIRST, ThreeValues.THIRD,
                        ThreeValues.THIRD, ThreeValues.SECOND)
                },
                new Object[]{Arrays.asList(
                        ThreeValues.THIRD, ThreeValues.FIRST,
                        ThreeValues.FIRST, ThreeValues.THIRD,
                        ThreeValues.SECOND, ThreeValues.SECOND, ThreeValues.FIRST)
                }
        };
    }

    @Test
    @Parameters
    public void shouldSortBooleanValuesSequentially(List<Boolean> array, boolean headValue) {
        booleanValueSequentialSort(array, headValue);

        int headValuePosition = array.lastIndexOf(headValue);
        boolean headValueIsInTheBeginning = array.subList(0, headValuePosition).stream().allMatch(i -> i == headValue);
        boolean allOtherValuesAreNotAsHeadOne = array.subList(headValuePosition + 1, array.size()).stream().allMatch(i -> i == !headValue);
        assertTrue(headValueIsInTheBeginning && allOtherValuesAreNotAsHeadOne);
    }

    public Object parametersForShouldSortBooleanValuesSequentially() {
        return new Object[]{
                new Object[]{asList(true, true, false, false, true, false), false},
                new Object[]{asList(true, true, false, false, true, false), true},
                new Object[]{asList(false, true, false, false, true, false), true},
                new Object[]{asList(false, true, false, false, true, true), false}
        };
    }

    @Test
    @Parameters
    public void shouldAddOneToArrayAsNumber(List<Integer> number, List<Integer> incremented) {
        plusOne(number);
        assertEquals(incremented, number);
    }

    public Object parametersForShouldAddOneToArrayAsNumber() {
        return new Object[]{
                new Object[]{
                        new ArrayList<>(asList(1, 2, 3)),
                        new ArrayList<>(asList(1, 2, 4))
                },
                new Object[]{
                        new ArrayList<>(asList(1, 2, 9)),
                        new ArrayList<>(asList(1, 3, 0))
                },
                new Object[]{
                        new ArrayList<>(asList(9, 9)),
                        new ArrayList<>(asList(1, 0, 0))
                }
        };
    }

    @Test
    @Parameters
    public void shouldDefineIfEndCanBeReached(List<Integer> board, boolean canReachEnd) {
        assertEquals(canReachEnd, canReachEnd(board));
    }

    public Object parametersForShouldDefineIfEndCanBeReached() {
        return new Object[]{
                new Object[]{asList(1, 1, 1), true},
                new Object[]{asList(3, 3, 0, 0, 0, 0, 1), false},
                new Object[]{asList(3, 2, 1, 1, 1, 0), true},
                new Object[]{asList(3, 2, 1, 1, 0, 0), false}
        };
    }

    @Test
    @Parameters
    public void shouldFindMinNumberOfStepsRequiredToWin(List<Integer> board, int minSteps) {
        assertEquals(minSteps, minNumberOfStepsToReachEnd(board));
    }

    public Object parametersForShouldFindMinNumberOfStepsRequiredToWin() {
        return new Object[]{
                new Object[]{asList(1, 1, 0), 2},
                new Object[]{asList(3, 2, 1, 1, 1, 0), 3},
                new Object[]{asList(3, 2, 1, 3, 1, 1), 2},
                new Object[]{asList(9, 2, 1, 3, 1, 1), 1},
                new Object[]{asList(1, 2, 9, 1, 1, 1), 3}
        };
    }

    @Test
    @Parameters
    public void shouldDeleteAllOccurrencesOfElementInArray(List<Integer> array, int key) {
        deleteKeyIn(array, key);
        assertFalse(array.stream().filter(i -> i == key).findAny().isPresent());
    }

    public Object parametersForShouldDeleteAllOccurrencesOfElementInArray() {
        return new Object[]{
                new Object[]{asList(1, 2, 3, 4, 5, 6), 1},
                new Object[]{asList(1, 1, 3, 4, 5, 6), 1},
                new Object[]{asList(1, 1, 3, 4, 1, 6), 1},
                new Object[]{asList(1, 1, 1, 4, 1, 6), 1},
                new Object[]{asList(1, 1, 1, 4, 1, 6, 1), 1},
                new Object[]{asList(1, 1, 1, 4, 1, 1, 1), 1},
                new Object[]{asList(), 1},
                new Object[]{asList(5, 5), 6}
        };
    }

    @Test
    @Parameters
    public void shouldDeleteDuplicatesInSortedArray(List<Integer> array) {
        List<Integer> expected = new ArrayList<>(new HashSet<>(array));
        removeDuplicatesInSorted(array);
        assertEquals(expected, array.stream().filter(i -> i != 0).collect(Collectors.toList()));
    }

    public Object parametersForShouldDeleteDuplicatesInSortedArray() {
        return new Object[]{
                new Object[]{asList(1, 1, 1, 2, 3, 3, 3, 3, 4, 5, 5)},
                new Object[]{asList(1, 1, 1, 1)},
                new Object[]{asList(1, 2, 3, 4, 5)}
        };
    }
}
