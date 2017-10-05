package io.zgeeks.algorithm.hackerrank;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.AbstractMap.SimpleImmutableEntry;
import static java.util.stream.Collectors.toMap;

public final class ContainersOfBalls {

    private final int[] input;
    private final Map<Fraction, Double> fractions;

    public ContainersOfBalls(int[] input) {
        this.input = input;
        this.fractions = initFractions();
    }

    public void compute() {
        Arrays.stream(input).forEach(index -> fractions.keySet().forEach(key -> fractions.compute(key,
                                    (k, v) -> fn().apply(index).apply(input.length).apply(k, v))));
    }

    public double positiveRate() {
        return fractions.get(Fraction.POSITIVE);
    }

    public double negativeRate() {
        return fractions.get(Fraction.NEGATIVE);
    }

    public double zeroesRate() {
        return fractions.get(Fraction.ZEROES);
    }

    private static Map<Fraction,Double> initFractions() {
        return Arrays.stream(Fraction.values())
            .map(fraction -> new SimpleImmutableEntry<>(fraction, 0.0))
            .collect(toMap(SimpleImmutableEntry::getKey, SimpleImmutableEntry::getValue));
    }

    private static Function<Integer, Function<Integer, BiFunction<? super Fraction, ? super Double, ? extends Double>>> fn() {
        return input -> n -> (k, v) -> k.test(input) ? v = ((v * n) + 1 ) / n : v;
    }

    private enum Fraction {

        POSITIVE(v -> v > 0), NEGATIVE(v -> v < 0), ZEROES(v -> v == 0);

        private final Predicate<Integer> predicate;

        Fraction(Predicate<Integer> predicate) {
            this.predicate = predicate;
        }

        boolean test(int value) {
            return predicate.test(value);
        }
    }
}
