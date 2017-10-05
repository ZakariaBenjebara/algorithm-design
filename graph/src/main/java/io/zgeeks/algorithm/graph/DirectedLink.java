package io.zgeeks.algorithm.graph;

import org.immutables.value.Value;

import java.util.Iterator;

import static java.util.Arrays.*;


public interface DirectedLink<N> extends Iterable<N> {

    @Value.Parameter
    N node1();
    @Value.Parameter
    N node2();

    static <N> DirectedLink<N> unordered(N node1, N node2) {
        return null;
    }

    default N neighbourOf(Object node) {
        if (node.equals(node1())) {
            return node2();
        } else if (node.equals(node2())) {
            return node1();
        }
        throw new IllegalArgumentException("DirectedLink " + this + " does not contain node " + node);
    }

    @Override
    @Value.Lazy
    default Iterator<N> iterator() {
        return asList(node1(), node2()).iterator();
    }
}
