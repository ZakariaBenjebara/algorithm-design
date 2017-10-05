package io.zgeeks.util;


import org.junit.Assert;
import org.junit.Test;

/**
 */
public class ConvertesTest {

    @Test
    public void twoSum() {
        int[] expected = TwoSum.twoSum(new int[]{1, 3, 4, 5}, 7);
        Assert.assertEquals(expected[0], 1);
        Assert.assertEquals(expected[1], 2);
    }
}