package io.zgeeks.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by benjebarazakaria on 21/08/2017.
 */
public final class TwoSum {

    private TwoSum() {
    }

    public static int[] twoSum(int[] nums, int target) {
        final Map<Integer, Integer> sol = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int rest = target -  nums[i];
            if (sol.containsKey(rest)) {
                return new int[]{sol.get(rest), i};
            }
            sol.put(nums[i], i);

        }
        throw new IllegalStateException("No solution found");
    }
}
