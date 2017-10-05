package io.zgeeks.algorithm.hackerrank.queenattack;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class QueenAttackBuilder {

    private QueenAttackBuilder() {}

    public static Set<QueenAttack.Square> createBoard(int dim) {
        if (dim <= 0) {
            throw new IllegalArgumentException("Incorrect dimension of the board");
        }
        final Set<QueenAttack.Square> board = new HashSet<>();
        for (int i = 1; i <= dim; i++) {
            for (int j = 1; j <= dim; j++) {
                final QueenAttack.Square square = search(i, j, board).orElse(new QueenAttack.Active(i, j));
                final Map<QueenAttack.Direction, QueenAttack.Square> squareMap = LinksBuilder.builder().limit(dim)
                                                                            .source(square).build(board);
                square.merge(squareMap);
                board.add(square);
                squareMap.values().stream().forEach(board::add);
            }
        }
        return board;
    }

    public static void markAsObstacle(int line, int col, Set<QueenAttack.Square> board) {
        search(line, col, board).ifPresent(
            square -> {
                final QueenAttack.Obstacle obstacle = new QueenAttack.Obstacle(line, col);
                Optional.ofNullable(square.squares).ifPresent(boardSet -> boardSet.entrySet().stream().forEach(
                    entry -> {
                        QueenAttack.Square s = entry.getValue();
                        Optional.ofNullable(s.squares).ifPresent(s2 -> s2.put(QueenAttack.Direction.from(s, square), obstacle));
                    }
                ));
                board.remove(square);
                board.add(obstacle);
            }
        );
    }

    private static Optional<QueenAttack.Square> search(int line, int col, Set<QueenAttack.Square> board) {
        return board.stream()
            .filter(s -> s.equals(new QueenAttack.Active(line, col)))
            .findAny();
    }
}
