package io.zgeeks.algorithm.hackerrank.queenattack;

import io.zgeeks.algorithm.util.Tuple;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class LinksBuilder {

    private QueenAttack.Square source;
    private int limit = Integer.MAX_VALUE;

    private LinksBuilder() {}

    static LinksBuilder builder() {
        return new LinksBuilder();
    }

    LinksBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    LinksBuilder source(QueenAttack.Square square) {
        this.source = square;
        return this;
    }

    private boolean checkLimits(int line, int col) {
        return (line <= limit && line > 0)
            && (col <= limit && col > 0);
    }

    Map<QueenAttack.Direction, QueenAttack.Square> build(Set<QueenAttack.Square> set) {
        return Arrays.stream(QueenAttack.Direction.values())
            .map(direction -> {
                final Tuple<Integer, Integer> tuple = direction.to().apply(source);
                if (!checkLimits(tuple.t1(), tuple.t2())) {
                    return new AbstractMap.SimpleImmutableEntry<>(direction, new QueenAttack.Obstacle(tuple.t1(), tuple.t2()));
                }
                return set.stream().filter(s -> new QueenAttack.Active(tuple.t1(), tuple.t2()).equals(s))
                    .findAny().map(s -> new AbstractMap.SimpleImmutableEntry<>(direction, s))
                    .orElse(new AbstractMap.SimpleImmutableEntry<>(direction, new QueenAttack.Active(tuple.t1(), tuple.t2())));})
            .collect(Collectors.toMap(AbstractMap.SimpleImmutableEntry::getKey, AbstractMap.SimpleImmutableEntry::getValue));
    }
}
