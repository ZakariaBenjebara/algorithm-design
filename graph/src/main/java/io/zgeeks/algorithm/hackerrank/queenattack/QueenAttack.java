package io.zgeeks.algorithm.hackerrank.queenattack;

import io.zgeeks.algorithm.util.Tuple;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class QueenAttack {

    private final Set<Square> board;

    public QueenAttack(Set<Square> board) {
        this.board = board;
    }

    public int numberOfAttacks(int line, int col) {
        return board.stream()
            .filter(s -> s.col == col && s.line == line)
            .findFirst().orElseThrow(NoSuchElementException::new)
            .spread();
    }

    public static abstract class Square implements Iterator<Square> {

        private final int line;
        private final int col;
        protected Map<Direction, Square> squares;
        protected Direction to;

        public Square(int line, int col) {
            this.line = line;
            this.col = col;
        }

        public int spread() {
            return squares.entrySet().stream()
                .filter(entry -> entry.getKey() != Direction.NIL)
                .mapToInt(entry -> this.spreadTo(Direction.from(this, entry.getValue())))
                .sum();
        }

        int spreadTo(Direction direction) {
            Square current = squares.get(direction).to(direction);
            if (current instanceof Obstacle) {
                return 0;
            }
            int sum = 1;
            while (current.hasNext()) {
                sum += 1;
                current = current.next().to(direction);
            }
            return sum;
        }

        private static Map<Direction, Square> initializeNiegbours() {
            return Arrays.stream(Direction.values())
                .map(d -> new AbstractMap.SimpleImmutableEntry<>(d, new Active(-1, -1)))
                .collect(Collectors.toMap(AbstractMap.SimpleImmutableEntry::getKey, AbstractMap.SimpleImmutableEntry::getValue));
        }

        public void merge(Map<Direction, Square> source) {
            squares = initializeNiegbours();
            squares.entrySet().stream().forEach(entry -> squares.compute(entry.getKey(), (k, v) -> source.get(k)));
        }

        public Square to(Direction direction) {
            this.to = direction;
            return this;
        }

        @Override
        public final int hashCode() {
            return Objects.hash(line, col);
        }

        @Override
        public final boolean equals(Object obj) {
            if (obj != null) {
                Square that = (Square) obj;
                return (this.col == that.col)
                    && (this.line == that.line);
            }
            return false;
        }

        @Override
        public final String toString() {
            return "(" + line + ", " + col + ")";
        }
    }

    public static final class Obstacle extends Square {

        public Obstacle(int line, int col) {
            super(line, col);
        }

        @Override
        public final boolean hasNext() {
            return false;
        }

        @Override
        public final Square next() {
            return null;
        }
    }

    public static final class Active extends Square {

        Active(int line, int col) {
            super(line, col);
        }

        @Override
        public boolean hasNext() {
            return !(next() instanceof Obstacle);
        }

        @Override
        public final Square next() {
            return squares.get(to);
        }
    }

    public enum Direction {
        TOP((s1, s2) -> s1.col == s2.col && s1.line < s2.line, s -> new Tuple<>(s.line + 1, s.col)),
        TOP_RIGHT((s1, s2) -> s1.col < s2.col && s1.line < s2.line, s -> new Tuple<>(s.line + 1, s.col + 1)),
        TOP_LEFT((s1, s2) -> s1.col > s2.col && s1.line < s2.line, s -> new Tuple<>(s.line + 1, s.col - 1)),
        BOTTOM((s1, s2) -> s1.col == s2.col && s1.line > s2.line, s -> new Tuple<>(s.line - 1, s.col)),
        BOTTOM_RIGHT((s1, s2) -> s1.col < s2.col && s1.line > s2.line, s -> new Tuple<>(s.line - 1, s.col + 1)),
        BOTTOM_LEFT((s1, s2) -> s1.col > s2.col && s1.line > s2.line, s -> new Tuple<>(s.line - 1, s.col - 1)),
        RIGHT((s1, s2) -> s1.col < s2.col && s1.line == s2.line, s -> new Tuple<>(s.line , s.col + 1)),
        LEFT((s1, s2) -> s1.col > s2.col && s1.line == s2.line, s -> new Tuple<>(s.line , s.col - 1)),
        NIL((s1, s2) -> false, s -> new Tuple<>(0 , 0));

        private final BiPredicate<Square, Square> predicate;
        private final Function<Square, Tuple<Integer, Integer>> to;

        Direction(BiPredicate<Square, Square> predicate, Function<Square, Tuple<Integer, Integer>> to) {
            this.predicate = predicate;
            this.to = to;
        }

        boolean test(Square s1, Square s2) {
            return predicate.test(s1, s2);
        }

        static Direction from(Square s1, Square s2) {
            return Arrays.stream(values())
                .filter(direction -> direction.test(s1, s2))
                .findFirst()
                .orElse(NIL);
        }

        public Function<Square, Tuple<Integer, Integer>> to() {
            return to;
        }
    }
}
