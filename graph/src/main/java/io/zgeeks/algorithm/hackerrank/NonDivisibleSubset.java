package io.zgeeks.algorithm.hackerrank;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.Math.*;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public final class NonDivisibleSubset {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Map<Integer, Long> counting = IntStream.range(0, n)
                                .map(i -> sc.nextInt() % k)
                                .boxed()
                                .collect(groupingBy(identity(), counting()));

        int to = (k % 2 == 1) ? (k / 2) : (k / 2 - 1);

        long result = IntStream.range(1, to + 1)
                .mapToLong(i -> max(getValue(i, counting), getValue(k - i,counting)))
                .reduce((counting.containsKey(0) ? 1 : 0) + ((k % 2 == 0) && (counting.containsKey(k / 2)) ? 1 : 0), (a, b) -> a + b);

        System.out.println(result);
    }

    private static long getValue(int key, Map<Integer, Long> values) {
        return values.containsKey(key) ? values.get(key) : 0;
    }
}
