package com.github.tkachuko.origramming.interview.strings;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.tkachuko.origramming.interview.strings.Strings.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class StringsTest {

    @Test
    @Parameters
    public void shouldDecodeExcelColumn(String excelColumn, int decoded) {
        assertEquals(decodeExcelColumnNumber(excelColumn), decoded);
    }

    public Object parametersForShouldDecodeExcelColumn() {
        return new Object[]{
                new Object[]{"A", 1},
                new Object[]{"AA", 27},
                new Object[]{"ZZ", 702}
        };
    }

    @Test
    @Parameters
    public void shouldReverseString(String string, String reversed) {
        assertEquals(reversed, reverse(string));
    }

    public Object parametersForShouldReverseString() {
        return new Object[]{
                new Object[]{"A", "A"},
                new Object[]{"AB", "BA"},
                new Object[]{"ZZABBZ123", "321ZBBAZZ"}
        };
    }

    @Test
    @Parameters
    public void shouldReverseRangeInString(char[] string, int start, int end, char[] reversed) {
        reverseRange(string, start, end);
        assertArrayEquals(reversed, string);
    }

    public Object parametersForShouldReverseRangeInString() {
        return new Object[]{
                new Object[]{"ABSSA".toCharArray(), 0, 2, "SBASA".toCharArray()},
                new Object[]{"ZZABBZ123".toCharArray(), 3, 5, "ZZAZBB123".toCharArray()}
        };
    }

    @Test
    @Parameters
    public void shouldReverseWordsInSentence(String string, String wordsReversed) {
        assertEquals(wordsReversed, reverseWordsInSentence(string));
    }

    public Object parametersForShouldReverseWordsInSentence() {
        return new Object[]{
                new Object[]{"Alice likes Bob", "Bob likes Alice"},
                new Object[]{"Hello World", "World Hello"},
                new Object[]{"SingleWord", "SingleWord"},
                new Object[]{"Reverse Words In Sentence", "Sentence In Words Reverse"}
        };
    }
}