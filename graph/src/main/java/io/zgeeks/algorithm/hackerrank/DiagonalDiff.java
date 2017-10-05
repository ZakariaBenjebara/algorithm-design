package io.zgeeks.algorithm.hackerrank;

import java.util.*;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiagonalDiff {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        Matrix matrix = new Matrix(size);
        IntStream.range(0, size).forEach(line -> IntStream.range(0, size).forEach(
                column -> matrix.put(sc.nextInt(), line)));
        System.out.println(matrix.diagonalDiff());
    }

    private static final class Matrix {
        final int size;
        final Map<Integer, List<Integer>> values;

        Matrix(int size) {
            this.size = size;
            this.values = initializeValues(size);
        }

        void put(int element, int dim) {
            if (dim < 0 || dim >= size) {
                throw new IllegalStateException("incorect dimention");
            }
            values.get(dim).add(element);
        }

        int diagonalDiff(int lowerLimit, int upperLimit) {
            return values.keySet().stream()
                .map(index -> new Tuple(values.get(index).get(index - lowerLimit),
                                        values.get(index).get(Math.abs(index - upperLimit))))
                .reduce((a, b) -> new Tuple(a.sum1 + b.sum1, a.sum2 + b.sum2)).orElseThrow(()
                                    -> new IllegalStateException("cannot ")).diff();
        }

        int diagonalDiff() {
            return diagonalDiff(0, size - 1);
        }

        static Map<Integer, List<Integer>> initializeValues(int dim) {
            if (dim <= 0 ) {
                throw new IllegalStateException("incorrect dimention");
            }
            return IntStream.range(0, dim).mapToObj(i -> new SimpleImmutableEntry<>(i, new ArrayList<Integer>()))
                .collect(Collectors.toMap(SimpleImmutableEntry::getKey, SimpleImmutableEntry::getValue));
        }
    }

    private static final class Tuple {
        final int sum1;
        final int sum2;

        Tuple(int sum1, int sum2) {
            this.sum1 = sum1;
            this.sum2 = sum2;
        }

        int diff() {
            return Math.abs(sum1 - sum2);
        }
    }
}
